package com.eduardozamit.certificationproject.modules.students.useCases;

import com.eduardozamit.certificationproject.modules.students.dto.StudentCertificationAnswerDTO;
import com.eduardozamit.certificationproject.modules.questions.entities.QuestionEntity;
import com.eduardozamit.certificationproject.modules.questions.repositories.QuestionRepository;
import com.eduardozamit.certificationproject.modules.students.dto.VerifyHasCertificationDTO;
import com.eduardozamit.certificationproject.modules.students.entities.AnswersCertificationsEntity;
import com.eduardozamit.certificationproject.modules.students.entities.CertificationStudentEntity;
import com.eduardozamit.certificationproject.modules.students.entities.StudentEntity;
import com.eduardozamit.certificationproject.modules.students.repositories.CertificationStudentRepository;
import com.eduardozamit.certificationproject.modules.students.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class StudentCertificationAnswersUseCase {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private CertificationStudentRepository certificationStudentRepository;

    @Autowired
    private VerifyIfHasCertificationUseCase verifyIfHasCertificationUseCase;

    public CertificationStudentEntity execute(StudentCertificationAnswerDTO dto) throws Exception {

        var hasCertification = this.verifyIfHasCertificationUseCase.execute(new VerifyHasCertificationDTO(dto.getEmail(), dto.getTechnology()));

        if (hasCertification) {
            throw new Exception("Você já tirou sua certificação");
        }

        // Buscar as alternativas das perguntas
        // - Correct or Wrong
        List<QuestionEntity> questionsEntity = questionRepository.findByTechnology(dto.getTechnology());
        List<AnswersCertificationsEntity> answersCertifications = new ArrayList<>();

        AtomicInteger correctAnswers = new AtomicInteger(0);

        dto.getQuestionsAnswers()
                .stream().forEach(questionAnswer -> {
                    var question = questionsEntity.stream()
                            .filter(q -> q.getId().equals(questionAnswer.getQuestionID())).findFirst().get();

                    var findCorrectAlternative = question.getAlternatives().stream()
                            .filter(alternative -> alternative.isCorrect()).findFirst().get();

                    if (findCorrectAlternative.getId().equals(questionAnswer.getAlternativeID())) {
                        questionAnswer.setCorrect(true); //isCorrect:true
                        correctAnswers.incrementAndGet();
                    } else {
                        questionAnswer.setCorrect(false); //isCorrect:false
                    }

                    var answersCertificationsEntity = AnswersCertificationsEntity.builder()
                            .answerID(questionAnswer.getAlternativeID())
                            .questionID(questionAnswer.getQuestionID())
                                    .isCorrect(questionAnswer.isCorrect()).build();

                    answersCertifications.add(answersCertificationsEntity);
                });

        // Verificar se existe student pelo email
        var student = studentRepository.findByEmail(dto.getEmail());
        UUID studentID;
        if (student.isEmpty()) {
            var studentCreated = StudentEntity.builder().email(dto.getEmail()).build();
            studentCreated = studentRepository.save(studentCreated);
            studentID = studentCreated.getId();
        } else {
            studentID = student.get().getId();
        }


        CertificationStudentEntity certificationStudentEntity =
                CertificationStudentEntity.builder()
                        .technology(dto.getTechnology())
                        .studentID(studentID)
                        .grade(correctAnswers.get())
                        .build();

        var certificationStudentCreated = certificationStudentRepository.save(certificationStudentEntity);

        answersCertifications.stream().forEach(answersCertification -> {
            answersCertification.setCertificationID(certificationStudentEntity.getId());
            answersCertification.setCertificationStudentEntity(certificationStudentEntity);
        });

        certificationStudentEntity.setAnswersCertificationsEntities(answersCertifications);

        certificationStudentRepository.save(certificationStudentEntity);

        return certificationStudentCreated;

        // Salvar as informações da certificação
    }
}

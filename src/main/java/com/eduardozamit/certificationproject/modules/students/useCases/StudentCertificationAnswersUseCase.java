package com.eduardozamit.certificationproject.modules.students.useCases;

import com.eduardozamit.certificationproject.modules.students.dto.StudentCertificationAnswerDTO;
import com.eduardozamit.certificationproject.modules.questions.entities.QuestionEntity;
import com.eduardozamit.certificationproject.modules.questions.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentCertificationAnswersUseCase {

    @Autowired
    private QuestionRepository questionRepository;

    public StudentCertificationAnswerDTO execute(StudentCertificationAnswerDTO dto) {
        // Buscar as alternativas das perguntas
        // - Correct or Wrong
        List<QuestionEntity> questionsEntity = questionRepository.findByTechnology(dto.getTechnology());


        dto.getQuestionsAnswers()
                .stream().forEach(questionAnswer -> {
                    var question = questionsEntity.stream()
                            .filter(q -> q.getId().equals(questionAnswer.getQuestionID())).findFirst().get();

                    var findCorrectAlternative = question.getAlternatives().stream()
                            .filter(alternative -> alternative.isCorrect()).findFirst().get();

                    if (findCorrectAlternative.getId().equals(questionAnswer.getAlternativeID())) {
                        questionAnswer.setCorrect(true); //isCorrect:true
                    } else {
                        questionAnswer.setCorrect(false); //isCorrect:false
                    }
                });

        return dto;

        // Salvar as informações da certificação
    }
}

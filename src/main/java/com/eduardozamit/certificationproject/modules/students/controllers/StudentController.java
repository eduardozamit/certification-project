package com.eduardozamit.certificationproject.modules.students.controllers;

import com.eduardozamit.certificationproject.modules.students.dto.StudentCertificationAnswerDTO;
import com.eduardozamit.certificationproject.modules.students.dto.VerifyHasCertificationDTO;
import com.eduardozamit.certificationproject.modules.students.useCases.StudentCertificationAnswersUseCase;
import com.eduardozamit.certificationproject.modules.students.useCases.VerifyIfHasCertificationUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private VerifyIfHasCertificationUseCase verifyIfHasCertificationUseCase;

    @Autowired
    private StudentCertificationAnswersUseCase studentCertificationAnswersUseCase;

    @PostMapping("/verifyIfHasCertification")
    public String verifyIfHasCertification(@RequestBody VerifyHasCertificationDTO verifyHasCertificationDTO) {
        // Email
        // Technology
        System.out.println(verifyHasCertificationDTO);
        return "Usuário pode fazer a prova";
    }

    @PostMapping("/certification/answer")
    public StudentCertificationAnswerDTO certificationAnswer(@RequestBody StudentCertificationAnswerDTO studentCertificationAnswerDTO)  {
        return this.studentCertificationAnswersUseCase.execute(studentCertificationAnswerDTO);
    }
}

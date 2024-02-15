package com.eduardozamit.certificationproject.modules.certifications.controllers;

import com.eduardozamit.certificationproject.modules.certifications.useCase.Top10RankingUseCase;
import com.eduardozamit.certificationproject.modules.students.entities.CertificationStudentEntity;
import com.eduardozamit.certificationproject.modules.students.repositories.CertificationStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ranking")
public class RankingController {

    @Autowired
    private Top10RankingUseCase top10RankingUseCase;

    @GetMapping("/top10")
    public List<CertificationStudentEntity> top10() {
        return this.top10RankingUseCase.execute();
    }
}

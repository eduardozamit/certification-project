package com.eduardozamit.certificationproject.modules.certifications.useCase;

import com.eduardozamit.certificationproject.modules.students.entities.CertificationStudentEntity;
import com.eduardozamit.certificationproject.modules.students.repositories.CertificationStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Top10RankingUseCase {

    @Autowired
    private CertificationStudentRepository certificationStudentRepository;
    public List<CertificationStudentEntity> execute() {
        var result = this.certificationStudentRepository.findTop10ByOrderByGradeDesc();
        return result;
    }
}

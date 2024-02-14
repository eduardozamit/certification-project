package com.eduardozamit.certificationproject.modules.students.questions.repositories;

import com.eduardozamit.certificationproject.modules.students.questions.entities.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, UUID> {
    List<QuestionEntity> findByTechnology(String technology);
}

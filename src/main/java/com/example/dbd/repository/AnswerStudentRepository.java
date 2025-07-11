package com.example.dbd.repository;

import com.example.dbd.models.AnswerStudent;
import com.example.dbd.models.DetailEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerStudentRepository extends JpaRepository<AnswerStudent, Integer> {
    List<AnswerStudent> findByDetailEvaluation(DetailEvaluation detailEvaluation);
}

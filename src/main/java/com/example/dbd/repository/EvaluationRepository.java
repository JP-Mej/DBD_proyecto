package com.example.dbd.repository;

import com.example.dbd.models.Evaluation;
import com.example.dbd.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EvaluationRepository extends JpaRepository<Evaluation, Integer> {
    List<Evaluation> findByStudent(Student student);
}

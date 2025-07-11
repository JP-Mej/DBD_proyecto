package com.example.dbd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.dbd.models.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
}

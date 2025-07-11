package com.example.dbd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.dbd.models.Difficulty;

@Repository
public interface DifficultyRepository extends JpaRepository<Difficulty, Integer> {
}

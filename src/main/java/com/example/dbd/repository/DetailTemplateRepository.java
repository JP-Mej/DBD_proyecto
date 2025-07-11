package com.example.dbd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.dbd.models.DetailTemplate;

@Repository
public interface DetailTemplateRepository extends JpaRepository<DetailTemplate, Integer> {
}

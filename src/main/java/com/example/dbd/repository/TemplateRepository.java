package com.example.dbd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.dbd.models.Template;

@Repository
public interface TemplateRepository extends JpaRepository<Template, Integer> {
}


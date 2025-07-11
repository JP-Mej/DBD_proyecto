package com.example.dbd.repository;

import com.example.dbd.models.EvaluationTemplateDetail;
import com.example.dbd.models.EvaluationTemplateDetailId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluationTemplateDetailRepository extends JpaRepository<EvaluationTemplateDetail, EvaluationTemplateDetailId> {

    // 🔍 Buscar todos los bloques de una evaluación por su ID
    List<EvaluationTemplateDetail> findByIdEvaluation(Integer idEvaluation);
}

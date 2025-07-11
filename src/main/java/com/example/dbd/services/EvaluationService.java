package com.example.dbd.services;

import com.example.dbd.models.Evaluation;
import com.example.dbd.models.Student;
import com.example.dbd.repository.EvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluationService {

    @Autowired
    private EvaluationRepository evaluationRepository;

    public List<Evaluation> obtenerEvaluacionesPorEstudiante(Student student) {
        return evaluationRepository.findByStudent(student);
    }
}

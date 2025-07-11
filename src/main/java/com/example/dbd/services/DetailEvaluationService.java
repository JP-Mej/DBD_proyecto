package com.example.dbd.services;

import com.example.dbd.models.*;
import com.example.dbd.repository.AnswerRepository;
import com.example.dbd.repository.AnswerStudentRepository;
import com.example.dbd.repository.DetailEvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailEvaluationService {

    @Autowired
    private DetailEvaluationRepository detailEvaluationRepository;

    @Autowired
    private AnswerStudentRepository answerStudentRepository;

    @Autowired
    private AnswerRepository answerRepository;

    public List<DetailEvaluation> obtenerDetallesPorEvaluacion(Evaluation evaluation) {
        return detailEvaluationRepository.findAll().stream()
                .filter(de -> de.getEvaluation().getId().equals(evaluation.getId()))
                .toList();
    }

    public List<Answer> obtenerRespuestasPorPregunta(Question question) {
        return answerRepository.findByQuestion(question);
    }

    public boolean estudianteAcertoPregunta(DetailEvaluation de) {
        List<AnswerStudent> respuestas = answerStudentRepository.findByDetailEvaluation(de);

        // Retorna true si alguna respuesta del estudiante es a una alternativa que es correcta
        return respuestas.stream()
                .anyMatch(answerStudent -> {
                    Answer ans = answerStudent.getAnswer();
                    return ans != null && ans.getIsCorrect();  // Aquí se usa la relación con Answer
                });
    }

    public List<AnswerStudent> obtenerRespuestasEstudiantePorDetalleEvaluacion(DetailEvaluation detailEvaluation) {
        return answerStudentRepository.findByDetailEvaluation(detailEvaluation);
    }
}

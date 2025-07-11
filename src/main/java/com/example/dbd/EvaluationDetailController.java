package com.example.dbd;

import com.example.dbd.models.*;
import com.example.dbd.repository.EvaluationRepository;
import com.example.dbd.services.DetailEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/evaluation-details")
public class EvaluationDetailController {

    @Autowired
    private EvaluationRepository evaluationRepository;

    @Autowired
    private DetailEvaluationService detailEvaluationService;

    @GetMapping("/{evaluationId}")
    public ResponseEntity<?> obtenerDetallesEvaluacion(@PathVariable Integer evaluationId) {
        Optional<Evaluation> evalOpt = evaluationRepository.findById(evaluationId);

        if (evalOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Evaluation evaluation = evalOpt.get();

        List<DetailEvaluation> detalles = detailEvaluationService.obtenerDetallesPorEvaluacion(evaluation);
        List<Map<String, Object>> preguntasDTO = new ArrayList<>();

        for (DetailEvaluation de : detalles) {
            Map<String, Object> preguntaMap = new HashMap<>();
            Question q = de.getQuestion();

            preguntaMap.put("numero", de.getNumberQuestion());
            preguntaMap.put("texto", q.getDescriptionQuestion());
            preguntaMap.put("detailEvaluationId", de.getId());
            // Todas las respuestas posibles a esta pregunta
            List<Answer> respuestas = detailEvaluationService.obtenerRespuestasPorPregunta(q);
            List<AnswerStudent> respuestasEstudiante = detailEvaluationService.obtenerRespuestasEstudiantePorDetalleEvaluacion(de);

            List<Map<String, Object>> alternativasDTO = new ArrayList<>();
            for (Answer ans : respuestas) {
                Map<String, Object> altMap = new HashMap<>();
                altMap.put("descripcion", ans.getDescription());
                altMap.put("esCorrecta", ans.getIsCorrect());
                altMap.put("idRespuesta", ans.getId());
                boolean esSeleccionada = respuestasEstudiante.stream()
                        .anyMatch(as -> as.getAnswer().getId().equals(ans.getId())); // comparando ID de Answer

                altMap.put("esSeleccionada", esSeleccionada);
                alternativasDTO.add(altMap);
            }

            preguntaMap.put("alternativas", alternativasDTO);

            // Ver si acertó la pregunta (si al menos una de las respuestas del estudiante fue a una alternativa correcta)
            boolean esCorrecta = respuestasEstudiante.stream()
                    .anyMatch(as -> as.getAnswer() != null && as.getAnswer().getIsCorrect());

            preguntaMap.put("esCorrecta", esCorrecta);

            preguntasDTO.add(preguntaMap);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("nombreEvaluacion", evaluation.getTemplate().getNameTemplate());
        response.put("nota", evaluation.getGrade());
        response.put("preguntas", preguntasDTO);

        return ResponseEntity.ok(response);
    }
}

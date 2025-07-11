package com.example.dbd;

import com.example.dbd.models.Answer;
import com.example.dbd.models.AnswerStudent;
import com.example.dbd.models.DetailEvaluation;
import com.example.dbd.repository.AnswerRepository;
import com.example.dbd.repository.AnswerStudentRepository;
import com.example.dbd.repository.DetailEvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/respuestas")
public class RespuestaEvaluacionController {

    @Autowired
    private DetailEvaluationRepository detailEvaluationRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private AnswerStudentRepository answerStudentRepository;

    // DTO para recibir respuestas
    public static class RespuestaDTO {
        public Integer detailEvaluationId;
        public Integer answerId;
    }

    @PostMapping
    public ResponseEntity<?> guardarRespuestas(@RequestBody List<RespuestaDTO> respuestas) {
        try {
            for (RespuestaDTO dto : respuestas) {
                Optional<DetailEvaluation> detalleOpt = detailEvaluationRepository.findById(dto.detailEvaluationId);
                Optional<Answer> respuestaOpt = answerRepository.findById(dto.answerId);

                if (detalleOpt.isPresent() && respuestaOpt.isPresent()) {
                    AnswerStudent as = new AnswerStudent();
                    as.setDetailEvaluation(detalleOpt.get());
                    as.setAnswer(respuestaOpt.get());
                    answerStudentRepository.save(as);
                } else {
                    return ResponseEntity.badRequest().body("Detalle o respuesta no encontrada para algún registro.");
                }
            }

            return ResponseEntity.ok("Respuestas guardadas exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al guardar respuestas: " + e.getMessage());
        }
    }
}

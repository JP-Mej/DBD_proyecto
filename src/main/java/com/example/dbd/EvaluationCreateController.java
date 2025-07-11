package com.example.dbd;

import com.example.dbd.models.*;
import com.example.dbd.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/create-evaluation")
public class EvaluationCreateController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TemplateRepository templateRepository;

    @Autowired
    private EvaluationRepository evaluationRepository;

    @Autowired
    private DetailTemplateRepository detailTemplateRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private DetailEvaluationRepository detailEvaluationRepository;

    @Autowired
    private EvaluationTemplateDetailRepository evaluationTemplateDetailRepository;

    @PostMapping("/{username}")
    public ResponseEntity<?> crearEvaluacionParaEstudiante(@PathVariable String username) {
        Student student = studentRepository.findByUser(username).orElse(null);
        if (student == null) return ResponseEntity.badRequest().body("Estudiante no encontrado");

        List<Template> templates = templateRepository.findAll();
        if (templates.isEmpty()) return ResponseEntity.badRequest().body("No hay plantillas");

        Template plantilla = templates.get(new Random().nextInt(templates.size()));

        List<DetailTemplate> bloques = detailTemplateRepository.findAll().stream()
                .filter(d -> d.getTemplate().getId().equals(plantilla.getId()))
                .collect(Collectors.toList());

        List<Integer> cursosUnicos = bloques.stream()
                .map(d -> d.getCourse().getId())
                .distinct()
                .collect(Collectors.toList());

        Collections.shuffle(cursosUnicos);
        List<Integer> cursosElegidos = cursosUnicos.stream().limit(3).collect(Collectors.toList());

        Evaluation evaluacion = new Evaluation();
        evaluacion.setStudent(student);
        evaluacion.setTemplate(plantilla);
        evaluationRepository.save(evaluacion); // para tener ID generado

        int numeroPregunta = 1;

        for (DetailTemplate bloque : bloques) {
            if (!cursosElegidos.contains(bloque.getCourse().getId())) continue;

            List<Question> posiblesPreguntas = questionRepository.findAll().stream()
                    .filter(q ->
                            q.getCourse().getId().equals(bloque.getCourse().getId()) &&
                            q.getDifficulty().getId().equals(bloque.getDifficulty().getId())
                    )
                    .collect(Collectors.toList());

            Collections.shuffle(posiblesPreguntas);

            int cantidad = bloque.getNumberOfQuestions();
            for (int i = 0; i < Math.min(cantidad, posiblesPreguntas.size()); i++) {
                DetailEvaluation detalle = new DetailEvaluation();
                detalle.setEvaluation(evaluacion);
                detalle.setQuestion(posiblesPreguntas.get(i));
                detalle.setNumberQuestion(numeroPregunta++);
                detailEvaluationRepository.save(detalle);
            }

            EvaluationTemplateDetail etd = new EvaluationTemplateDetail();
            etd.setIdEvaluation(evaluacion.getId());
            etd.setIdDetailTemplate(bloque.getId());
            etd.setQuestionsAssigned(Math.min(cantidad, posiblesPreguntas.size()));
            etd.setPointsObtained(0);
            evaluationTemplateDetailRepository.save(etd);
        }

        return ResponseEntity.ok("Evaluación creada exitosamente con ID: " + evaluacion.getId());
    }
}

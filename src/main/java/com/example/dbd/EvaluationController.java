package com.example.dbd;

import com.example.dbd.models.Evaluation;
import com.example.dbd.models.Student;
import com.example.dbd.repository.StudentRepository;
import com.example.dbd.services.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

// DTO limpio sin dificultad
class EvaluationDTO {
    public Integer id;
    public String resultadoNombre;
    public String nota;

    public EvaluationDTO(Evaluation e) {
        this.id = e.getId();
        this.resultadoNombre = e.getTemplate().getNameTemplate();
        this.nota = (e.getGrade() != null)
                ? String.format("%.2f", e.getGrade())
                : "Sin nota";
    }
}

@RestController
@RequestMapping("/api/evaluations")
public class EvaluationController {

    @Autowired
    private EvaluationService evaluationService;

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/{user}")
    public List<EvaluationDTO> getEvaluationsByUser(@PathVariable String user) {
        System.out.println("🔍 Buscando evaluaciones para el usuario: " + user);

        Student student = studentRepository.findByUser(user).orElse(null);

        if (student == null) {
            System.out.println("⚠️ Estudiante no encontrado para el usuario: " + user);
            return List.of();
        }

        List<Evaluation> evaluaciones = evaluationService.obtenerEvaluacionesPorEstudiante(student);

        System.out.println("📋 Total de evaluaciones encontradas: " + evaluaciones.size());

        return evaluaciones.stream()
                .map(EvaluationDTO::new)
                .collect(Collectors.toList());
    }
}

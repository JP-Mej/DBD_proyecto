package com.example.dbd;

import com.example.dbd.models.Student;
import com.example.dbd.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Registro")
public class RegistroStudent {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<?> registrarEstudiante(@RequestBody Student nuevoEstudiante) {
        try {
            // Debug para ver qué se recibió
            System.out.println("Recibido:");
            System.out.println("Usuario: " + nuevoEstudiante.getUser());
            System.out.println("Nombre: " + nuevoEstudiante.getFirstName());
            System.out.println("Apellido Paterno: " + nuevoEstudiante.getLastNameFather());
            System.out.println("Apellido Materno: " + nuevoEstudiante.getLastNameMother());
            System.out.println("Email: " + nuevoEstudiante.getEmail());
            System.out.println("Password: " + nuevoEstudiante.getPassword());

            // Validación de campos obligatorios
            if (nuevoEstudiante.getUser() == null || nuevoEstudiante.getUser().isBlank() ||
                nuevoEstudiante.getFirstName() == null || nuevoEstudiante.getFirstName().isBlank() ||
                nuevoEstudiante.getLastNameFather() == null || nuevoEstudiante.getLastNameFather().isBlank() ||
                nuevoEstudiante.getLastNameMother() == null || nuevoEstudiante.getLastNameMother().isBlank() ||
                nuevoEstudiante.getEmail() == null || nuevoEstudiante.getEmail().isBlank() ||
                nuevoEstudiante.getPassword() == null || nuevoEstudiante.getPassword().isBlank()) {
                return ResponseEntity.badRequest().body("Faltan campos obligatorios.");
            }

            if (studentService.getStudentByUser(nuevoEstudiante.getUser()).isPresent()) {
                return ResponseEntity.status(409).body("El usuario ya existe.");
            }

            if (studentService.getStudentByEmail(nuevoEstudiante.getEmail()).isPresent()) {
                return ResponseEntity.status(409).body("El correo ya está en uso.");
            }

            Student creado = studentService.createStudent(nuevoEstudiante);
            return ResponseEntity.ok("Registro exitoso para: " + creado.getUser());

        } catch (Exception e) {
            e.printStackTrace(); // Mostrará el verdadero error
            return ResponseEntity.status(500).body("Error interno: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }
}

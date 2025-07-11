package com.example.dbd;

import com.example.dbd.models.Student;
import com.example.dbd.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;




@RestController
@RequestMapping("/api/InicioSesion")
public class InicioSesion {

    @Autowired
    private StudentRepository studentRepository;

    // Clase interna para recibir el JSON con las credenciales
    public static class LoginRequest {
        public String user;
        public String password;

        // Getters y Setters opcionales si se quiere usar con serializadores externos
    }

        // DTO interno (puede ir dentro de InicioSesion.java)
    public static class StudentDTO {
        public String user;
        public String firstName;
        public String middleName;
        public String lastNameFather;
        public String lastNameMother;
        public String email;

        public StudentDTO(Student student) {
            this.user = student.getUser();
            this.firstName = student.getFirstName();
            this.middleName = student.getMiddleName();
            this.lastNameFather = student.getLastNameFather();
            this.lastNameMother = student.getLastNameMother();
            this.email = student.getEmail();
        }
    }


    @PostMapping
    public ResponseEntity<?> verificarCredenciales(@RequestBody LoginRequest loginRequest) {
        Optional<Student> studentOpt = studentRepository.findByUser(loginRequest.user);

        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();

            if (student.getPassword().equals(loginRequest.password)) {
                StudentDTO dto = new StudentDTO(student);
                return ResponseEntity.ok(dto); // Devuelve los datos del estudiante sin la contraseña
            } else {
                return ResponseEntity.status(401).body("Contraseña incorrecta.");
            }
        } else {
            return ResponseEntity.status(404).body("Usuario no encontrado.");
        }
    }

}

package com.example.dbd.services;

import com.example.dbd.models.Student;
import com.example.dbd.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // Crear un nuevo estudiante
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    // Obtener todos los estudiantes
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Obtener un estudiante por su ID
    public Optional<Student> getStudentById(Integer id) {
        return studentRepository.findById(id);
    }

    // Actualizar un estudiante
    public Optional<Student> updateStudent(Integer id, Student updatedStudent) {
        return studentRepository.findById(id).map(existingStudent -> {
            existingStudent.setUser(updatedStudent.getUser());
            existingStudent.setFirstName(updatedStudent.getFirstName());
            existingStudent.setMiddleName(updatedStudent.getMiddleName());
            existingStudent.setLastNameMother(updatedStudent.getLastNameMother());
            existingStudent.setLastNameFather(updatedStudent.getLastNameFather());
            existingStudent.setEmail(updatedStudent.getEmail());
            existingStudent.setPassword(updatedStudent.getPassword());
            return studentRepository.save(existingStudent);
        });
    }

    // Eliminar un estudiante por su ID
    public boolean deleteStudent(Integer id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Buscar por email
    public Optional<Student> getStudentByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    // Buscar por nombre de usuario
    public Optional<Student> getStudentByUser(String user) {
        return studentRepository.findByUser(user);
    }
}

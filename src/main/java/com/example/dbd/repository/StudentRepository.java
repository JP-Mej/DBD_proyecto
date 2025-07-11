package com.example.dbd.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.dbd.models.Student;

import java.util.Optional;


@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    // Aquí puedes agregar métodos personalizados si deseas
    Optional<Student> findByEmail(String email);
    Optional<Student> findByUser(String user);
}

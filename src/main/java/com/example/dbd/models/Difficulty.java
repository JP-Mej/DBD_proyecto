package com.example.dbd.models;

import jakarta.persistence.*;

@Entity
@Table(name = "DIFFICULTY")
public class Difficulty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDDIFFICULTY")
    private Integer id;

    @Column(name = "DESCRIPTION", nullable = false, length = 10)
    private String description;

    // Constructor vacío (requerido por JPA)
    public Difficulty() {
    }

    // Constructor opcional (por conveniencia)
    public Difficulty(String description) {
        this.description = description;
    }

    // Getters y setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

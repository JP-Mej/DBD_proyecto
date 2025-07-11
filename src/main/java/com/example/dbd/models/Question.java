package com.example.dbd.models;

import jakarta.persistence.*;

@Entity
@Table(name = "QUESTION")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDQUESTION")
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "IDCOURSE", referencedColumnName = "IDCOURSE", nullable = false,
                foreignKey = @ForeignKey(name = "FK_QUESTION_COURSE"))
    private Course course;

    @ManyToOne(optional = false)
    @JoinColumn(name = "IDDIFFICULTY", referencedColumnName = "IDDIFFICULTY", nullable = false,
                foreignKey = @ForeignKey(name = "FK_QUESTION_DIFFICULTY"))
    private Difficulty difficulty;

    @Column(name = "DESCRIPTIONQUESTION", nullable = false, length = 200)
    private String descriptionQuestion;

    // Constructor vacío requerido por JPA
    public Question() {
    }

    // Getters y setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public String getDescriptionQuestion() {
        return descriptionQuestion;
    }

    public void setDescriptionQuestion(String descriptionQuestion) {
        this.descriptionQuestion = descriptionQuestion;
    }
}


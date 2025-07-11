package com.example.dbd.models;

import jakarta.persistence.*;

@Entity
@Table(name = "ANSWER")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDANSWER")
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "IDQUESTION", referencedColumnName = "IDQUESTION", nullable = false,
                foreignKey = @ForeignKey(name = "FK_ANSWER_QUESTION"))
    private Question question;

    @Column(name = "IS_CORRECT", nullable = false)
    private Boolean isCorrect;

    @Column(name = "DESCRIPTIONANS", nullable = false, length = 200)
    private String description;

    // Constructor vacío requerido por JPA
    public Answer() {
    }

    // Getters y setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Boolean getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(Boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

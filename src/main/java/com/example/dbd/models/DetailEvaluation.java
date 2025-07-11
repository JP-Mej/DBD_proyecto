package com.example.dbd.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "DETAILEVALUATION")
public class DetailEvaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDDETAILEVALUATION")
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "IDEVALUATION", referencedColumnName = "IDEVALUATION", nullable = false)
    private Evaluation evaluation;

    @ManyToOne(optional = false)
    @JoinColumn(name = "IDQUESTION", referencedColumnName = "IDQUESTION", nullable = false)
    private Question question;

    @Column(name = "NUMBERQUESTION", nullable = false)
    @Min(0)
    @Max(100)
    private Integer numberQuestion;

    // Getters y setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Evaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Integer getNumberQuestion() {
        return numberQuestion;
    }

    public void setNumberQuestion(Integer numberQuestion) {
        this.numberQuestion = numberQuestion;
    }
}

package com.example.dbd.models;

import jakarta.persistence.*;

@Entity
@Table(name = "ANSWERSTUDENT")
public class AnswerStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDANSWERSTUDENT")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDDETAILEVALUATION", nullable = false)
    private DetailEvaluation detailEvaluation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDANSWER", nullable = false)
    private Answer answer;

    // Constructors
    public AnswerStudent() {}

    public AnswerStudent(DetailEvaluation detailEvaluation, Answer answer) {
        this.detailEvaluation = detailEvaluation;
        this.answer = answer;
    }

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DetailEvaluation getDetailEvaluation() {
        return detailEvaluation;
    }

    public void setDetailEvaluation(DetailEvaluation detailEvaluation) {
        this.detailEvaluation = detailEvaluation;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }
}

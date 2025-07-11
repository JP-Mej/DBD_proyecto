package com.example.dbd.models;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "EVALUATIONTEMPLATEDETAIL")
@IdClass(EvaluationTemplateDetailId.class)
public class EvaluationTemplateDetail implements Serializable {

    
    @Id
    @Column(name = "IDEVALUATION")
    private Integer idEvaluation;

    @Id
    @Column(name = "IDDETAILTEMPLATE")
    private Integer idDetailTemplate;

    @Column(name = "QUESTIONS_ASSIGNED", nullable = false)
    private Integer questionsAssigned;

    @Column(name = "POINTS_OBTAINED", nullable = false)
    private Integer pointsObtained = 0;

    // Getters y Setters

    public Integer getIdEvaluation() {
        return idEvaluation;
    }

    public void setIdEvaluation(Integer idEvaluation) {
        this.idEvaluation = idEvaluation;
    }

    public Integer getIdDetailTemplate() {
        return idDetailTemplate;
    }

    public void setIdDetailTemplate(Integer idDetailTemplate) {
        this.idDetailTemplate = idDetailTemplate;
    }

    public Integer getQuestionsAssigned() {
        return questionsAssigned;
    }

    public void setQuestionsAssigned(Integer questionsAssigned) {
        this.questionsAssigned = questionsAssigned;
    }

    public Integer getPointsObtained() {
        return pointsObtained;
    }

    public void setPointsObtained(Integer pointsObtained) {
        this.pointsObtained = pointsObtained;
    }
}

package com.example.dbd.models;

import java.io.Serializable;
import java.util.Objects;

public class EvaluationTemplateDetailId implements Serializable {

    private Integer idEvaluation;
    private Integer idDetailTemplate;

    public EvaluationTemplateDetailId() {}

    public EvaluationTemplateDetailId(Integer idEvaluation, Integer idDetailTemplate) {
        this.idEvaluation = idEvaluation;
        this.idDetailTemplate = idDetailTemplate;
    }

    // ✅ Getters y Setters
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EvaluationTemplateDetailId that)) return false;
        return Objects.equals(idEvaluation, that.idEvaluation)
            && Objects.equals(idDetailTemplate, that.idDetailTemplate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEvaluation, idDetailTemplate);
    }
}

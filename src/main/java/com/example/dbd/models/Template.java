package com.example.dbd.models;

import jakarta.persistence.*;

@Entity
@Table(name = "TEMPLATE")
public class Template {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDTEMPLATE")
    private Integer id;

    @Column(name = "NAMETEMPLATE", nullable = false, length = 50)
    private String nameTemplate;

    @Column(name = "NUMBERQUESTIONS_T")
    private Integer numberQuestionsS;

    @Column(name = "NUMBER_EASY", nullable = false)
    private Integer numberEasy;

    @Column(name = "NUMBER_MEDIUM", nullable = false)
    private Integer numberMedium;

    @Column(name = "NUMBER_HARD", nullable = false)
    private Integer numberHard;

    // Constructor vacío requerido por JPA
    public Template() {
    }

    // Getters y setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameTemplate() {
        return nameTemplate;
    }

    public void setNameTemplate(String nameTemplate) {
        this.nameTemplate = nameTemplate;
    }

    public Integer getNumberQuestionsS() {
        return numberQuestionsS;
    }

    public void setNumberQuestionsS(Integer numberQuestionsS) {
        this.numberQuestionsS = numberQuestionsS;
    }

    public Integer getNumberEasy() {
        return numberEasy;
    }

    public void setNumberEasy(Integer numberEasy) {
        this.numberEasy = numberEasy;
    }

    public Integer getNumberMedium() {
        return numberMedium;
    }

    public void setNumberMedium(Integer numberMedium) {
        this.numberMedium = numberMedium;
    }

    public Integer getNumberHard() {
        return numberHard;
    }

    public void setNumberHard(Integer numberHard) {
        this.numberHard = numberHard;
    }
}


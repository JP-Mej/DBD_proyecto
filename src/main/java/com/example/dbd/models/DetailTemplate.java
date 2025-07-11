package com.example.dbd.models;

import jakarta.persistence.*;

@Entity
@Table(name = "DETAILTEMPLATE")
public class DetailTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDDETAILTEMPLATE")
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "IDTEMPLATE", referencedColumnName = "IDTEMPLATE", 
                foreignKey = @ForeignKey(name = "FK_DETAILTEMPLATE_TEMPLATE"))
    private Template template;

    @ManyToOne(optional = false)
    @JoinColumn(name = "IDCOURSE", referencedColumnName = "IDCOURSE", 
                foreignKey = @ForeignKey(name = "FK_DETAILTEMPLATE_COURSE"))
    private Course course;

    @ManyToOne
    @JoinColumn(name = "IDDIFFICULTY", referencedColumnName = "IDDIFFICULTY",
                foreignKey = @ForeignKey(name = "FK_DETAILTEMPLATE_DIFFICULTY"))
    private Difficulty difficulty;

    @Column(name = "NUMBER_OF_QUESTIONS")
    private Integer numberOfQuestions;

    // Getters y Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
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

    public Integer getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(Integer numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }
}

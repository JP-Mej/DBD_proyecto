package com.example.dbd.models;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalTime;

@Entity
@Table(name = "EVALUATION")
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDEVALUATION")
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "IDSTUDENT", referencedColumnName = "IDSTUDENT",
                foreignKey = @ForeignKey(name = "FK_EVALUATION_STUDENT"))
    private Student student;

    @ManyToOne(optional = false)
    @JoinColumn(name = "IDTEMPLATE", referencedColumnName = "IDTEMPLATE",
                foreignKey = @ForeignKey(name = "FK_EVALUATION_TEMPLATE"))
    private Template template;

    @Column(name = "DURATION", nullable = false)
    private LocalTime duration = LocalTime.of(2, 0); // default '02:00:00'

    @Column(name = "GRADE", precision = 5, scale = 2)
    private BigDecimal grade;

    // Getters y Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }

    public BigDecimal getGrade() {
        return grade;
    }

    public void setGrade(BigDecimal grade) {
        this.grade = grade;
    }
}

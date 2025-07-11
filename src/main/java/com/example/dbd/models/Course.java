package com.example.dbd.models;
import jakarta.persistence.*;

@Entity
@Table(name = "COURSE")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDCOURSE")
    private Integer id;

    @Column(name = "NAMECOURSE", nullable = false, length = 20)
    private String nameCourse;

    public Integer getId(){
        return id;
    }
    public void setId(Integer id){
        this.id = id;
    }
    public String getNameCourse(){
        return nameCourse;
    }
    public void setNameCourse(String nameCourse){
        this.nameCourse = nameCourse; 
    }
    
}

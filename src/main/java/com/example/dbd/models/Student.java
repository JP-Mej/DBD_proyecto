package com.example.dbd.models;
import jakarta.persistence.*;

@Entity
@Table(name = "STUDENT")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDSTUDENT")
    private Integer id;

    @Column(name = "USER", nullable = false, unique = true, length = 20)
    private String user;

    @Column(name = "FIRSTNAME", nullable = false, length = 20)
    private String firstName;

    @Column(name = "MIDDLENAME", length = 20)
    private String middleName;

    @Column(name = "LASTNAMEMOTHER", nullable = false, length = 20)
    private String lastNameMother;

    @Column(name = "LASTNAMEFATHER", nullable = false, length = 20)
    private String lastNameFather;

    @Column(name = "EMAIL", nullable = false, unique = true, length = 40)
    private String email;

    @Column(name = "PASSWORD", nullable = false, length = 20)
    private String password;

    // Getters y Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastNameMother() {
        return lastNameMother;
    }

    public void setLastNameMother(String lastNameMother) {
        this.lastNameMother = lastNameMother;
    }

    public String getLastNameFather() {
        return lastNameFather;
    }

    public void setLastNameFather(String lastNameFather) {
        this.lastNameFather = lastNameFather;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

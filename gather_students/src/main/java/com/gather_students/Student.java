package com.gather_students;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String school;
    @Column
    private String district;
    @Column
    private String code;
    @Column
    private String className;
    @Column
    private String name;
    @Column
    private String dob;
    @Column
    private String sex;
    @Column
    private String birthplace;
    @Column
    private String ethnic;
    @Column
    private String address;
    @Column
    private String telephone;
    @Column
    private Double score1;
    @Column
    private Double score2;
    @Column
    private Double score3;
    @Column
    private Double score4;
    @Column
    private Double score5;
    @Column
    private Double totalScore5;
    @Column
    private Double totalScore;
    @Column
    private Double priorityScore;
    @Column
    private String note;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", school='" + school + '\'' +
                ", district='" + district + '\'' +
                ", code='" + code + '\'' +
                ", className='" + className + '\'' +
                ", name='" + name + '\'' +
                ", dob='" + dob + '\'' +
                ", sex='" + sex + '\'' +
                ", birthplace='" + birthplace + '\'' +
                ", ethnic='" + ethnic + '\'' +
                ", address='" + address + '\'' +
                ", telephone='" + telephone + '\'' +
                ", score1=" + score1 +
                ", score2=" + score2 +
                ", score3=" + score3 +
                ", score4=" + score4 +
                ", score5=" + score5 +
                ", totalScore5=" + totalScore5 +
                ", totalScore=" + totalScore +
                ", priorityScore=" + priorityScore +
                ", note='" + note + '\'' +
                '}';
    }
}

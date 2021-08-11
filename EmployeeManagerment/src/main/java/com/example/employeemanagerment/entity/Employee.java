package com.example.employeemanagerment.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Employee")
public class Employee {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "gender")
    private boolean gender;

    @ManyToOne
    @JoinColumn(name = "department_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Department department;
}

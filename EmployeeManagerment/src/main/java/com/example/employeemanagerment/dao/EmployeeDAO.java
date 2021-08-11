package com.example.employeemanagerment.dao;

import com.example.employeemanagerment.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeDAO extends JpaRepository<Employee,Integer> {

    @Query("SELECT e FROM Employee e WHERE e.id =?1")
    public Employee getEmployee(int id);




}

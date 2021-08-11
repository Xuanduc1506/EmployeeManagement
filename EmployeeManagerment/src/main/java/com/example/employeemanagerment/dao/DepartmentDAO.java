package com.example.employeemanagerment.dao;

import com.example.employeemanagerment.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DepartmentDAO extends JpaRepository<Department , Integer> {
    @Query("SELECT d FROM Department d WHERE d.id=?1")
    public Department getDepartmen(int id);

}

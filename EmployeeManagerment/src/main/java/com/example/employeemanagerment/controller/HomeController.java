package com.example.employeemanagerment.controller;

import com.example.employeemanagerment.dao.DepartmentDAO;
import com.example.employeemanagerment.dao.EmployeeDAO;
import com.example.employeemanagerment.entity.Department;
import com.example.employeemanagerment.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {
    @Autowired
    private DepartmentDAO departmentDAO;
    @Autowired
    private EmployeeDAO employeeDAO;

    @GetMapping("/Home")
    public String displayEmployee(Model model){
        Department department = departmentDAO.getDepartmen(1);
        List<Department> departments = departmentDAO.findAll();
        model.addAttribute("departments",departments);
        return "Home";
    }

    @GetMapping("/Delete")
    public String Delete(@RequestParam int id,Model model){
        Employee employee = employeeDAO.getEmployee(id);
        employeeDAO.delete(employee);
        return displayEmployee(model);
    }

    @GetMapping("/Edit")
    public String getEdit( @RequestParam int id,Model model){
        List<Department> departments = departmentDAO.findAll();
        Employee employee = employeeDAO.getEmployee(id);
        model.addAttribute("departments",departments);
        model.addAttribute("employee",employee);
        model.addAttribute("id",id);
        return "Edit";
    }

    @PostMapping("/Edit")
    public String postEdit(Model model,@RequestParam int id,@RequestParam String name,@RequestParam boolean gender,@RequestParam int department){
        Optional<Employee> employee = employeeDAO.findById(id);
        Department department1 = departmentDAO.getDepartmen(department);
        if(employee.isPresent()){
            Employee em = employee.get();
            em.setId(id);
            em.setName(name);
            em.setGender(gender);
            em.setDepartment(department1);
            employeeDAO.save(em);
            return displayEmployee(model);
        }else {
            model.addAttribute("message","edit fail");
            return "Error";
        }


    }

    @GetMapping("/Add")
    public String getAdd(Model model){
        List<Department> departments = departmentDAO.findAll();
        model.addAttribute("departments",departments);
        return "Add";
    }

    @PostMapping("/Add")
    public String postAdd(Model model ,@RequestParam int id,@RequestParam String name,@RequestParam boolean gender,@RequestParam int department) {
        Department department1 = departmentDAO.getDepartmen(department);
        Employee employee = employeeDAO.getEmployee(id);
        if (employee==null) {
            employee = new Employee();
            employee.setId(id);
            employee.setName(name);
            employee.setGender(gender);
            employee.setDepartment(department1);
            employeeDAO.save(employee);
            return displayEmployee(model);
        } else {
            model.addAttribute("message","This id is already exsit");
            return "Error";
        }
    }


}

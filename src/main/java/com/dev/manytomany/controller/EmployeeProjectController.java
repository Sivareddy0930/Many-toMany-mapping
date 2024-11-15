package com.dev.manytomany.controller;

import com.dev.manytomany.dto.AddEmployeeToProjectDto;
import com.dev.manytomany.dto.AddProjectToEmployeeDto;
import com.dev.manytomany.entity.Employee;
import com.dev.manytomany.entity.Project;
import com.dev.manytomany.service.EmployeeProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class EmployeeProjectController {

    @Autowired
    private EmployeeProjectService employeeProjectService;

    @PostMapping("/projects")
    public String createProjectWithEmployees(@RequestBody Project project) {
        return employeeProjectService.saveProjectWithEmployees(project);
    }

    @PostMapping("/employees")
    public String createEmployeeWithProjects(@RequestBody Employee employee) {
        return employeeProjectService.saveEmployeeWithProjects(employee);
    }

//    @PostMapping("/addEmployeeToProject/{id}")
//    public String addEmployeeToProject(@PathVariable("id") Long projectId, @RequestBody Employee employee) {
//        return employeeProjectService.addEmployeeToProjectBasedOnId(projectId, employee);
//    }

    @PostMapping("/addEmployeeToProject")
    public String addEmployeeToProject(@RequestBody AddEmployeeToProjectDto addEmployeeToProjectDto) {
        return employeeProjectService.addEmployeeToProjectBasedOnId(addEmployeeToProjectDto);
    }

    @PostMapping("/addProjectToEmployee")
    public String addProjectToEmployee(@RequestBody AddProjectToEmployeeDto addProjectToEmployeeDto) {
        return employeeProjectService.addProjectToEmployeeBasedOnId(addProjectToEmployeeDto);
    }


}
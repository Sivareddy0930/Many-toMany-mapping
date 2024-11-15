package com.dev.manytomany.service;

import com.dev.manytomany.entity.Employee;
import com.dev.manytomany.entity.Project;
import com.dev.manytomany.repository.EmployeeRepository;
import com.dev.manytomany.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
public class EmployeeProjectService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Transactional
    public String saveProjectWithEmployees(Project project) {
//        Project project = new Project();
//        project(Project.getProjectCode());
//        project.setProjectName(Project.getProjectName());
//        project.setTechnologyUsed(Project.getTechnologyUsed());
//
//        Set<Employee> employees = new HashSet<>();
//        for (EmployeeDTO employeeDTO : Project.getEmployees()) {
//            Employee employee = new Employee();
//            employee.setEmployeeId(employeeDTO.getEmployeeId());
//            employee.setName(employeeDTO.getName());
//            employee.setEmail(employeeDTO.getEmail());
//            employee.setTechnicalSkill(employeeDTO.getTechnicalSkill());
//
//            project.addEmployee(employee);
//            employees.add(employee);
//        }

         projectRepository.save(project);
        return "Project created successfully";
    }



    @Transactional
    public String saveEmployeeWithProjects(Employee employee) {

        employeeRepository.save(employee);
        return "Employee created successfully";
    }

    @Transactional
    public String addEmployeeToProjectBasedOnId(Long projectId, Employee employee) {

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found with ID: " + projectId));

//        Add the employee to the project and we here we have to link it bidirectionally.
        project.addEmployee(employee);

        employeeRepository.save(employee);

        return "Employee added to project successfully";
    }
}


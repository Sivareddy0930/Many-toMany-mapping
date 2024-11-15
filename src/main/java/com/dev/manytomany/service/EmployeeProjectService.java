package com.dev.manytomany.service;

import com.dev.manytomany.dto.AddEmployeeToProjectDto;
import com.dev.manytomany.dto.AddProjectToEmployeeDto;
import com.dev.manytomany.dto.EmployeeDto;
import com.dev.manytomany.entity.Employee;
import com.dev.manytomany.entity.Project;
import com.dev.manytomany.repository.EmployeeRepository;
import com.dev.manytomany.repository.ProjectRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Slf4j
@Service
public class EmployeeProjectService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public String saveProjectWithEmployees(Project project) {
        projectRepository.save(project);
        return "Project created successfully";
    }

    @Transactional
    public String saveEmployeeWithProjects(Employee employee) {

        employeeRepository.save(employee);
        return "Employee created successfully";
    }

    @Transactional
    public String addEmployeeToProjectBasedOnId(AddEmployeeToProjectDto addEmployeeToProjectDto) {

        Project projectById = projectRepository.findById(addEmployeeToProjectDto.getProjectId())
                .orElseThrow(() -> new RuntimeException("Project not found with ID: " + addEmployeeToProjectDto.getProjectId()));

        EmployeeDto employeeDto = addEmployeeToProjectDto.getEmployeeDto();

        Employee emp1 = new Employee(employeeDto.getName(),employeeDto.getEmail(),employeeDto.getTechnicalSkills());

        Employee savedEmployee = employeeRepository.save(emp1);

        String insertQuery = "INSERT INTO EMPLOYEE_PROJECT_TABLE (project_id, employee_id) VALUES (?, ?)";

        Query query = entityManager.createNativeQuery(insertQuery);
        query.setParameter(1, addEmployeeToProjectDto.getProjectId());
        query.setParameter(2, savedEmployee.getEid());

        query.executeUpdate();

        return "Employee added to project successfully";
    }

    @Transactional
    public String addProjectToEmployeeBasedOnId(AddProjectToEmployeeDto addProjectToEmployeeDto) {


        Optional<Employee> byId = employeeRepository.findById(addProjectToEmployeeDto.getEmployeeId());
        log.info("ById: " + addProjectToEmployeeDto.getEmployeeId());
        if (!byId.isPresent()) {
            throw new RuntimeException("Employee not found with ID: " + addProjectToEmployeeDto.getEmployeeId());
        }


        Project project = new Project(addProjectToEmployeeDto.getProjectDto().getName(), addProjectToEmployeeDto.getProjectDto().getDescription());
        Project savedProject = projectRepository.save(project);
        String insertQuery = "INSERT INTO EMPLOYEE_PROJECT_TABLE (project_id, employee_id) VALUES (?, ?)";

        Query query = entityManager.createNativeQuery(insertQuery);
        query.setParameter(1, savedProject.getId());
        query.setParameter(2, addProjectToEmployeeDto.getEmployeeId());

        query.executeUpdate();
        return "Project added to Employee successfully";

    }
}



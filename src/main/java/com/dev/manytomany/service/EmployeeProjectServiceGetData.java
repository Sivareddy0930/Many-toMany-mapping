package com.dev.manytomany.service;

import com.dev.manytomany.dto.EmployeeDto;
import com.dev.manytomany.dto.EmployeeResponseDto;
import com.dev.manytomany.dto.ProjectDto;
import com.dev.manytomany.dto.ProjectResponseDto;
import com.dev.manytomany.entity.Employee;
import com.dev.manytomany.entity.Project;
import com.dev.manytomany.repository.EmployeeRepository;
import com.dev.manytomany.repository.ProjectRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EmployeeProjectServiceGetData {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public EmployeeResponseDto getEmployeeDataByEmail(String employeeEmail) {

        Set<ProjectDto> projects = new HashSet<>();


        Optional<Employee> optionalEmployee = employeeRepository.findByEmail(employeeEmail);
        if (optionalEmployee.isPresent()) {
            Long eid = optionalEmployee.get().getEid();

            List<Long> projectIds;



            try{
                String selectQuery = "SELECT project_id FROM employee_project_table WHERE employee_id = :eid";
                Query query = entityManager.createNativeQuery(selectQuery);
                query.setParameter("eid", eid);

                 projectIds = query.getResultList();
            }
            catch (Exception e){
                throw new RuntimeException( "Error fetching employee and projects using native query");
            }

            for ( Long projectId : projectIds) {
                Optional<Project> byId = projectRepository.findById(projectId);
                if (byId.isPresent()) {

                    Project project = byId.get();
                    ProjectDto projectDto = new ProjectDto(project.getId(),project.getName(),project.getDescription());
                    projects.add(projectDto);
                }
            }

        } else {
            throw new RuntimeException( "Employee not found.");

        }

        EmployeeResponseDto response = new EmployeeResponseDto();
        Employee employee = optionalEmployee.get();
        response.setId(employee.getEid());
        response.setName(employee.getName());
        response.setEmail(employee.getEmail());
        response.setTechnicalSkills(employee.getTechnicalSkills());

        response.setProjects(projects);

        return response;

    }

    @Transactional
    public ProjectResponseDto getProjectDataById(Long id){

        Set<EmployeeDto> employees = new HashSet<>();


        Optional<Project> OptionalProject = projectRepository.findById(id);
        Project project = OptionalProject.get();


        if(OptionalProject.isPresent()){
            List<Long> employeeIds;

            try{
                String selectQuery = "SELECT employee_id FROM employee_project_table WHERE project_id = :id";
                Query query = entityManager.createNativeQuery(selectQuery);
                query.setParameter("id", id);
                employeeIds = query.getResultList();
            }
            catch (Exception e){
                throw new RuntimeException( "Error fetching projects and employees using native query");
            }


            for (Long employeeId : employeeIds) {

                Optional<Employee> byId = employeeRepository.findById(employeeId);
                Employee employee = byId.get();

                EmployeeDto employeeDto = new EmployeeDto(employee.getEid(),employee.getName(),employee.getEmail(),employee.getTechnicalSkills());
                employees.add(employeeDto);
            }


        }
        else{
            throw new RuntimeException("Project not found");
        }

        ProjectResponseDto response = new ProjectResponseDto();
        response.setId(project.getId());
        response.setName(project.getName());
        response.setDescription(project.getDescription());
        response.setEmployeeDto(employees);

        return response;


    }



}

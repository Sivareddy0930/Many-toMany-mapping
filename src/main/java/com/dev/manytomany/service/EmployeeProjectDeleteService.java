package com.dev.manytomany.service;

import com.dev.manytomany.repository.EmployeeRepository;
import com.dev.manytomany.repository.ProjectRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeProjectDeleteService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public String deleteEmployeeById(Long employeeId) {


        try{
            String deleteQuery = "DELETE FROM EMPLOYEE_PROJECT_TABLE WHERE employee_id = :id";
            Query query = entityManager.createNativeQuery(deleteQuery);
            query.setParameter("id", employeeId);
            query.executeUpdate();

            employeeRepository.deleteEmployeeById(employeeId);
        }
        catch(Exception e){
            throw new RuntimeException("Error while deleting employee from project: " + e.getMessage());
        }

        return "Employee with ID " + employeeId + " deleted successfully.";
    }


    @Transactional
    public String deleteProjectById(Long projectId) {


        try{
            String deleteQuery = "DELETE FROM EMPLOYEE_PROJECT_TABLE WHERE project_id = :id";
            Query query = entityManager.createNativeQuery(deleteQuery);
            query.setParameter("id", projectId);
            query.executeUpdate();
            projectRepository.deleteProjectById(projectId);
        }
        catch(Exception e){
            throw new RuntimeException("Error while deleting relation of Project and Employee: " + e.getMessage());
        }

        return "Project with ID " + projectId + " deleted successfully.";
    }
}

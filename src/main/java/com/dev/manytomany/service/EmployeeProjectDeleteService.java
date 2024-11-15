package com.dev.manytomany.service;

import com.dev.manytomany.repository.EmployeeRepository;
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
}

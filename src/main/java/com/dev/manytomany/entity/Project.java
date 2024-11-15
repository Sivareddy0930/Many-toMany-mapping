package com.dev.manytomany.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

//    @ManyToMany(mappedBy = "projects", cascade =  CascadeType.ALL,fetch = FetchType.LAZY)

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "EMPLOYEE_PROJECT_TABLE",
            joinColumns ={
                    @JoinColumn(name = "project_id", referencedColumnName = "id")
            } ,
            inverseJoinColumns = {
            @JoinColumn(name = "employee_id", referencedColumnName = "eid")
            }
    )
    private Set<Employee> employees;


    public void addEmployee(Employee employee) {
        if (!employees.contains(employee)) {  // Check if the employee is already associated with this project
            employees.add(employee);
            if (employee.getProjects() == null) {
                employee.setProjects(new HashSet<>());
            }
            employee.getProjects().add(this);  // Add the current project to the employee's projects
        }
    }


    public void removeEmployee(Employee employee) {
        employees.remove(employee);
        employee.getProjects().remove(this);
    }
}

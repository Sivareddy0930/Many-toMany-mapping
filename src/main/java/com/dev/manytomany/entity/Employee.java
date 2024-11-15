package com.dev.manytomany.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employees")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eid;
    private String name;
    private String email;
    private String technicalSkills;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "EMPLOYEE_PROJECT_TABLE",
            joinColumns = {
                    @JoinColumn(name = "employee_id", referencedColumnName = "eid")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "project_id", referencedColumnName = "id")
            }
    )
    private Set<Project> projects= new HashSet<>();

    public void addProject(Project project) {
        projects.add(project);
        project.getEmployees().add(this);
    }

    public void removeProject(Project project) {
        projects.remove(project);
        project.getEmployees().remove(this);
    }
}


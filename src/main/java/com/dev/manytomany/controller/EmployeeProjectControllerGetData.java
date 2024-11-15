package com.dev.manytomany.controller;

import com.dev.manytomany.dto.EmployeeResponseDto;
import com.dev.manytomany.dto.ProjectResponseDto;
import com.dev.manytomany.service.EmployeeProjectServiceGetData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EmployeeProjectControllerGetData {

    @Autowired
    private EmployeeProjectServiceGetData employeeProjectServiceGetData;

    @GetMapping("/employeeByEmail/{email}")
    public ResponseEntity<EmployeeResponseDto> getEmployeeEmail(@PathVariable String email){
        EmployeeResponseDto employeeDataByEmail = employeeProjectServiceGetData.getEmployeeDataByEmail(email);

        return new ResponseEntity<>(employeeDataByEmail, HttpStatus.OK);
    }


    @GetMapping("/projectById/{id}")
    public ResponseEntity<ProjectResponseDto> getEmployeeEmail(@PathVariable Long id){
        ProjectResponseDto projectDataById = employeeProjectServiceGetData.getProjectDataById(id);

        return new ResponseEntity<>(projectDataById, HttpStatus.OK);
    }
}

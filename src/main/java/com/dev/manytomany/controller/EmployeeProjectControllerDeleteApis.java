package com.dev.manytomany.controller;

import com.dev.manytomany.service.EmployeeProjectDeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EmployeeProjectControllerDeleteApis {
    @Autowired
    EmployeeProjectDeleteService service;

    @DeleteMapping("/deleteEmployee/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id){
        String response = service.deleteEmployeeById(id);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @DeleteMapping("/deleteProject/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable Long id){
        String response = service.deleteProjectById(id);
        return new ResponseEntity(response, HttpStatus.OK);
    }

}

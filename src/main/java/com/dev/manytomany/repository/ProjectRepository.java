package com.dev.manytomany.repository;

import com.dev.manytomany.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {}


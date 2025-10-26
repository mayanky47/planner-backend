package com.example.planner_backend.repo;

import com.example.planner_backend.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    // Custom query to ensure projects are returned ordered by creation date, descending
    List<Project> findAllByOrderByCreatedAtDesc();
}

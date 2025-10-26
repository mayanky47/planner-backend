package com.example.planner_backend.repo;

import com.example.planner_backend.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    // Custom query to find all tasks belonging to a specific project, ordered by due date
    List<Task> findByProjectIdOrderByDueDateAsc(Long projectId);
}

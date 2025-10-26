package com.example.planner_backend.controller;

import com.example.planner_backend.model.Project;
// import com.example.planner_backend.model.ProjectStrategyVersion; // Import the history model when available
import com.example.planner_backend.model.ProjectStrategyVersion;
import com.example.planner_backend.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

/**
 * REST Controller for managing Projects (Idea Themes).
 */
@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "*") // Allow React app access
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    // GET /api/projects
    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.findAllProjects();
    }

    // GET /api/projects/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Long id) {
        Project project = projectService.findProjectById(id)
                .orElseThrow(() -> new RuntimeException("Project not found for id: " + id));
        return ResponseEntity.ok(project);
    }

    // POST /api/projects
    @PostMapping
    public Project createProject(@RequestBody Project project) {
        System.out.println("Creating project: " + project);
        return projectService.saveProject(project);
    }

    // PUT /api/projects/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable Long id, @RequestBody Project projectDetails) {
        final Project updatedProject = projectService.updateProject(id, projectDetails);
        return ResponseEntity.ok(updatedProject);
    }

    // DELETE /api/projects/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }

    // --------------------------------------------------------------------------------
    // NEW HISTORY API ENDPOINT
    // --------------------------------------------------------------------------------

    /**
     * GET /api/projects/{id}/strategy-history
     * Retrieves the version history of the project's strategic plan.
     * The response should contain a List of ProjectStrategyVersion objects.
     */
    @GetMapping("/{id}/strategy-history")
    public ResponseEntity<List<ProjectStrategyVersion>> getProjectStrategyHistory(@PathVariable Long id) {
        // Call the service method to fetch the history for the given project ID.
        List<ProjectStrategyVersion> history = projectService.findProjectStrategyHistory(id);
        return ResponseEntity.ok(history);
    }
}

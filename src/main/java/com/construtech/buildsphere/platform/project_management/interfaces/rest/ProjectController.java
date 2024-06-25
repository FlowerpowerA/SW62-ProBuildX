package com.construtech.buildsphere.platform.project_management.interfaces.rest;

import com.construtech.buildsphere.platform.operationsManagement.domain.model.queries.GetAllTaskByProjectIdQuery;
import com.construtech.buildsphere.platform.operationsManagement.domain.model.queries.GetAllTeamsByProjectIdQuery;
import com.construtech.buildsphere.platform.operationsManagement.domain.model.queries.GetAllWorkersByProjectIdQuery;
import com.construtech.buildsphere.platform.operationsManagement.domain.model.valueobjects.Project;
import com.construtech.buildsphere.platform.operationsManagement.domain.services.*;
import com.construtech.buildsphere.platform.operationsManagement.interfaces.resources.TaskResource;
import com.construtech.buildsphere.platform.operationsManagement.interfaces.resources.TeamResource;
import com.construtech.buildsphere.platform.operationsManagement.interfaces.resources.WorkerResource;
import com.construtech.buildsphere.platform.operationsManagement.interfaces.transform.TaskResourceFromEntityAssembler;
import com.construtech.buildsphere.platform.operationsManagement.interfaces.transform.TeamResourceFromEntityAssembler;
import com.construtech.buildsphere.platform.operationsManagement.interfaces.transform.WorkerResourceFromEntityAssembler;
import com.construtech.buildsphere.platform.project_management.domain.model.aggregates.Dashboard;
import com.construtech.buildsphere.platform.project_management.domain.model.commands.CreateProjectCommand;
import com.construtech.buildsphere.platform.project_management.domain.model.commands.UpdateProjectCommand;
import com.construtech.buildsphere.platform.project_management.domain.model.commands.DeleteProjectCommand;
import com.construtech.buildsphere.platform.project_management.domain.model.queries.GetAllProjectsQuery;
import com.construtech.buildsphere.platform.project_management.domain.model.queries.GetProjectByIdQuery;
import com.construtech.buildsphere.platform.project_management.domain.services.ProjectCommandService;
import com.construtech.buildsphere.platform.project_management.domain.services.ProjectQueryService;
import com.construtech.buildsphere.platform.project_management.interfaces.rest.resources.CreateProjectResource;
import com.construtech.buildsphere.platform.project_management.interfaces.rest.resources.ProjectResource;
import com.construtech.buildsphere.platform.project_management.interfaces.rest.transform.CreateProjectCommandFromResourceAssembler;
import com.construtech.buildsphere.platform.project_management.interfaces.rest.transform.ProjectResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/projects", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Projects", description = "Projects Management Endpoints")
public class ProjectController {
    private final ProjectCommandService projectCommandService;
    private final ProjectQueryService projectQueryService;

    private final TaskQueryService taskQueryService;
    private final TeamQueryService teamQueryService;
    private final WorkerQueryService workerQueryService;

    public ProjectController(ProjectCommandService projectCommandService, ProjectQueryService projectQueryService, TaskQueryService taskQueryService, TeamQueryService teamQueryService, WorkerQueryService workerQueryService) {
        this.projectCommandService = projectCommandService;
        this.projectQueryService = projectQueryService;
        this.taskQueryService = taskQueryService;
        this.teamQueryService = teamQueryService;
        this.workerQueryService = workerQueryService;
    }

    @PostMapping
    public ResponseEntity<ProjectResource> createProject(@RequestBody CreateProjectResource resource) {
        var createProjectCommand = CreateProjectCommandFromResourceAssembler.toCommandFromResource(resource);
        var dashboard = projectCommandService.handle(createProjectCommand);
        if (dashboard.isEmpty()) return ResponseEntity.badRequest().build();
        var projectResource = ProjectResourceFromEntityAssembler.toResourceFromEntity(dashboard.get().getProject());
        return new ResponseEntity<>(projectResource, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectResource> updateProject(@PathVariable Long id, @RequestBody CreateProjectResource resource) {
        var updateProjectCommand = new UpdateProjectCommand(
                id,
                resource.name(),
                resource.description(),
                resource.location(),
                resource.startDate(),
                resource.expectedEndDate(),
                resource.budget(),
                resource.urlImage(),
                resource.userId()
        );
        var dashboard = projectCommandService.handle(updateProjectCommand);
        if (dashboard.isEmpty()) return ResponseEntity.badRequest().build();
        var projectResource = ProjectResourceFromEntityAssembler.toResourceFromEntity(dashboard.get().getProject());
        return ResponseEntity.ok(projectResource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectCommandService.handle(new DeleteProjectCommand(id));
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ProjectResource>> getAllProjects() {
        var dashboards = projectQueryService.handle(new GetAllProjectsQuery());
        var projectResources = dashboards.stream()
                .map(dashboard -> ProjectResourceFromEntityAssembler.toResourceFromEntity(dashboard.getProject()))
                .toList();
        return ResponseEntity.ok(projectResources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResource> getProjectById(@PathVariable Long id) {
        var dashboard = projectQueryService.handle(new GetProjectByIdQuery(id));
        if (dashboard.isEmpty()) return ResponseEntity.notFound().build();
        var projectResource = ProjectResourceFromEntityAssembler.toResourceFromEntity(dashboard.get().getProject());
        return ResponseEntity.ok(projectResource);
    }

    @GetMapping("/{projectId}/tasks")
    public ResponseEntity<List<TaskResource>> getAllTaskByProjectId(@PathVariable Long projectId){
        var project = new Project(projectId);
        var getAllTasksByProjectIdQuery = new GetAllTaskByProjectIdQuery(project);
        var tasks = taskQueryService.handle(getAllTasksByProjectIdQuery);
        var taskResources = tasks.stream().map(TaskResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(taskResources);
    }

    @GetMapping("/{projectId}/teams")
    public ResponseEntity<List<TeamResource>> getAllTeamsByProjectId(@PathVariable Long projectId){
        var project = new Project(projectId);
        var getAllTeamsByProjectIdQuery = new GetAllTeamsByProjectIdQuery(project);
        var teams = teamQueryService.handle(getAllTeamsByProjectIdQuery);
        var teamResources = teams.stream().map(TeamResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(teamResources);
    }

    @GetMapping("{projectId}/workers")
    public  ResponseEntity<List<WorkerResource>> getAllWorkersByProjectId(@PathVariable Long projectId){
        var project = new Project(projectId);
        var getAllWorkersByProjectIdQuery = new GetAllWorkersByProjectIdQuery(project);
        var workers = workerQueryService.handle(getAllWorkersByProjectIdQuery);
        var workerResources = workers.stream().map(WorkerResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(workerResources);
    }
}

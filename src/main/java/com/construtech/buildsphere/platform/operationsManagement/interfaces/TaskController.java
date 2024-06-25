package com.construtech.buildsphere.platform.operationsManagement.interfaces;


import com.construtech.buildsphere.platform.operationsManagement.domain.model.commands.DeleteTaskCommand;
import com.construtech.buildsphere.platform.operationsManagement.domain.model.queries.GetAllTaskByProjectIdQuery;
import com.construtech.buildsphere.platform.operationsManagement.domain.model.queries.GetAllTaskByTeamIdQuery;
import com.construtech.buildsphere.platform.operationsManagement.domain.model.queries.GetTaskByIdQuery;
import com.construtech.buildsphere.platform.operationsManagement.domain.model.valueobjects.Project;
import com.construtech.buildsphere.platform.operationsManagement.domain.services.TaskCommandService;
import com.construtech.buildsphere.platform.operationsManagement.domain.services.TaskQueryService;
import com.construtech.buildsphere.platform.operationsManagement.interfaces.resources.CreateTaskResource;
import com.construtech.buildsphere.platform.operationsManagement.interfaces.resources.TaskResource;
import com.construtech.buildsphere.platform.operationsManagement.interfaces.resources.UpdateTaskResource;
import com.construtech.buildsphere.platform.operationsManagement.interfaces.transform.CreateTaskCommandFromResourceAssembler;
import com.construtech.buildsphere.platform.operationsManagement.interfaces.transform.TaskResourceFromEntityAssembler;
import com.construtech.buildsphere.platform.operationsManagement.interfaces.transform.UpdateTaskCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Tasks", description = "Tasks Management Endpoints")
public class TaskController {

    private final TaskCommandService taskCommandService;
    private final TaskQueryService taskQueryService;

    public TaskController(TaskCommandService taskCommandService, TaskQueryService taskQueryService) {
        this.taskCommandService = taskCommandService;
        this.taskQueryService = taskQueryService;
    }

    @PostMapping
    public ResponseEntity<TaskResource> createTask(@RequestBody CreateTaskResource createTaskResource){
        var createTaskCommand = CreateTaskCommandFromResourceAssembler.toCommandFromResource(createTaskResource);
        var taskId = taskCommandService.handle(createTaskCommand);
        if(taskId == 0L){
            return ResponseEntity.badRequest().build();
        }
        var getTaskByIdQuery = new GetTaskByIdQuery(taskId);
        var task = taskQueryService.handle(getTaskByIdQuery);
        if(task.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        var taskResource = TaskResourceFromEntityAssembler.toResourceFromEntity(task.get());
        return new ResponseEntity<>(taskResource, HttpStatus.CREATED);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskResource> getTask(@PathVariable Long taskId){
        var getTaskByIdQuery = new GetTaskByIdQuery(taskId);
        var task = taskQueryService.handle(getTaskByIdQuery);
        if (task.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var taskResource = TaskResourceFromEntityAssembler.toResourceFromEntity(task.get());
        return ResponseEntity.ok(taskResource);
    }


    @GetMapping("/teams/{teamId}")
    public ResponseEntity<List<TaskResource>> getAllTaskByTeamId(@PathVariable Long teamId){
        var getAllTasksByTeamIdQuery = new GetAllTaskByTeamIdQuery(teamId);
        var tasks = taskQueryService.handle(getAllTasksByTeamIdQuery);
        var taskResources = tasks.stream().map(TaskResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(taskResources);
    }


    @PutMapping("/{taskId}")
    public ResponseEntity<TaskResource> updateTask(@PathVariable Long taskId, @RequestBody UpdateTaskResource updateTaskResource){
        var updateTaskCommand = UpdateTaskCommandFromResourceAssembler.toCommandFromResource(taskId, updateTaskResource);
        var updateTask = taskCommandService.handle(updateTaskCommand);
        if(updateTask.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        var taskResource = TaskResourceFromEntityAssembler.toResourceFromEntity(updateTask.get());
        return ResponseEntity.ok(taskResource);
    }
    @DeleteMapping("/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable Long taskId){
        var deleteTaskCommand = new DeleteTaskCommand(taskId);
        taskCommandService.handle(deleteTaskCommand);
        return ResponseEntity.ok("Task deleted successfully");
    }


}

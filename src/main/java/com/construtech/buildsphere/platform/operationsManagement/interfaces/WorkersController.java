package com.construtech.buildsphere.platform.operationsManagement.interfaces;


import com.construtech.buildsphere.platform.operationsManagement.domain.model.commands.DeleteWorkerCommand;
import com.construtech.buildsphere.platform.operationsManagement.domain.model.queries.GetAllWorkersByProjectIdQuery;
import com.construtech.buildsphere.platform.operationsManagement.domain.model.queries.GetWorkerByIdQuery;
import com.construtech.buildsphere.platform.operationsManagement.domain.model.valueobjects.Project;
import com.construtech.buildsphere.platform.operationsManagement.domain.services.WorkerCommandService;
import com.construtech.buildsphere.platform.operationsManagement.domain.services.WorkerQueryService;
import com.construtech.buildsphere.platform.operationsManagement.interfaces.resources.CreateWorkerResource;
import com.construtech.buildsphere.platform.operationsManagement.interfaces.resources.UpdateWorkerResource;
import com.construtech.buildsphere.platform.operationsManagement.interfaces.resources.WorkerResource;
import com.construtech.buildsphere.platform.operationsManagement.interfaces.transform.CreateWorkerCommandFromResourceAssembler;
import com.construtech.buildsphere.platform.operationsManagement.interfaces.transform.UpdateWorkerCommandFromResourceAssembler;
import com.construtech.buildsphere.platform.operationsManagement.interfaces.transform.WorkerResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/workers", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Workers", description = "Workers Management Endpoints")
public class WorkersController {

    private final WorkerCommandService workerCommandService;
    private final WorkerQueryService workerQueryService;

    public WorkersController(WorkerCommandService workerCommandService, WorkerQueryService workerQueryService){
        this.workerCommandService = workerCommandService;
        this.workerQueryService = workerQueryService;
    }

    @PostMapping
    public ResponseEntity<WorkerResource> createWorker(@RequestBody CreateWorkerResource createWorkerResource){
        var createWorkerCommand = CreateWorkerCommandFromResourceAssembler.toCommandFromResource(createWorkerResource);
        var workerId = workerCommandService.handle(createWorkerCommand);
        if(workerId == 0L){
            return ResponseEntity.badRequest().build();
        }
        var getWorkerByIdQuery = new GetWorkerByIdQuery(workerId);
        var worker = workerQueryService.handle(getWorkerByIdQuery);
        if(worker.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        var workerResource = WorkerResourceFromEntityAssembler.toResourceFromEntity(worker.get());
        return new ResponseEntity<>(workerResource, HttpStatus.CREATED);
    }

    @GetMapping("/{workerId}")
    public ResponseEntity<WorkerResource> getWorker(@PathVariable Long workerId){
        var getWorkerByIdQuery = new GetWorkerByIdQuery(workerId);
        var worker = workerQueryService.handle(getWorkerByIdQuery);
        if(worker.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var workerResource = WorkerResourceFromEntityAssembler.toResourceFromEntity(worker.get());
        return ResponseEntity.ok(workerResource);
    }

    @GetMapping("/projectId/{projectId}")
    public  ResponseEntity<List<WorkerResource>> getAllWorkersByProjectId(@PathVariable Long projectId){
        var project = new Project(Math.toIntExact(projectId));
        var getAllWorkersByProjectIdQuery = new GetAllWorkersByProjectIdQuery(project);
        var workers = workerQueryService.handle(getAllWorkersByProjectIdQuery);
        var workerResources = workers.stream().map(WorkerResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(workerResources);
    }

    @PutMapping("/{workerId}")
    public ResponseEntity<WorkerResource> updateWorker(@PathVariable Long workerId, @RequestBody UpdateWorkerResource updateWorkerResource){
        var updateWorkerCommand = UpdateWorkerCommandFromResourceAssembler.toCommandFromResource(workerId, updateWorkerResource);
        var updateWorker = workerCommandService.handle(updateWorkerCommand);
        if(updateWorker.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        var workerResource = WorkerResourceFromEntityAssembler.toResourceFromEntity(updateWorker.get());
        return ResponseEntity.ok(workerResource);
    }

    @DeleteMapping("/{workerId}")
    public ResponseEntity<?> deleteWorker(@PathVariable Long workerId){
        var deleteWorkerCommand = new DeleteWorkerCommand(workerId);
        workerCommandService.handle(deleteWorkerCommand);
        return ResponseEntity.ok("Worker deleted successfully");
    }
}

package com.construtech.buildsphere.platform.resourceManagement.interfaces.rest;

import com.construtech.buildsphere.platform.resourceManagement.domain.model.commands.DeleteMachineCommand;
import com.construtech.buildsphere.platform.resourceManagement.domain.model.queries.GetAllMachinesByProjectIdQuery;
import com.construtech.buildsphere.platform.resourceManagement.domain.model.queries.GetMachineByIdQuery;
import com.construtech.buildsphere.platform.resourceManagement.domain.model.valueobjects.Project;
import com.construtech.buildsphere.platform.resourceManagement.domain.services.MachineCommandService;
import com.construtech.buildsphere.platform.resourceManagement.domain.services.MachineQueryService;
import com.construtech.buildsphere.platform.resourceManagement.interfaces.rest.resources.CreateMachineResource;
import com.construtech.buildsphere.platform.resourceManagement.interfaces.rest.resources.MachineResource;
import com.construtech.buildsphere.platform.resourceManagement.interfaces.rest.resources.UpdateMachineResource;
import com.construtech.buildsphere.platform.resourceManagement.interfaces.rest.transform.CreateMachineCommandFromResourceAssembler;
import com.construtech.buildsphere.platform.resourceManagement.interfaces.rest.transform.MachineResourceFromEntityAssembler;
import com.construtech.buildsphere.platform.resourceManagement.interfaces.rest.transform.UpdateMachineCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/machines", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Machines", description = "Machines Management Endpoint")
public class MachineController {
    private final MachineCommandService machineCommandService;
    private final MachineQueryService machineQueryService;

    public MachineController(MachineCommandService machineCommandService, MachineQueryService machineQueryService) {
        this.machineCommandService = machineCommandService;
        this.machineQueryService = machineQueryService;
    }

    @PostMapping
    public ResponseEntity<MachineResource> createMachine(@RequestBody CreateMachineResource createMachineResource) {
        var createMachineCommand = CreateMachineCommandFromResourceAssembler.toCommandFromResource(createMachineResource);
        var machineId = machineCommandService.handle(createMachineCommand);
        if (machineId == 0L) {
            return ResponseEntity.badRequest().build();
        }

        var getMachineByIdQuery = new GetMachineByIdQuery(machineId);
        var machine = machineQueryService.handle(getMachineByIdQuery);
        if (machine.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var machineResource = MachineResourceFromEntityAssembler.toResourceFromEntity(machine.get());
        return new ResponseEntity<>(machineResource, HttpStatus.CREATED);
    }

    @GetMapping("/{machineId}")
    public ResponseEntity<MachineResource> getMachine(@PathVariable Long machineId) {
        var getMachineByIdQuery = new GetMachineByIdQuery(machineId);
        var machine = machineQueryService.handle(getMachineByIdQuery);
        if (machine.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var machineResource = MachineResourceFromEntityAssembler.toResourceFromEntity(machine.get());
        return ResponseEntity.ok(machineResource);
    }

    @GetMapping("/projectId/{projectId}")
    public ResponseEntity<List<MachineResource>> getAllMachinesByProjectId(@PathVariable Long projectId) {
        var project = new Project(Math.toIntExact(projectId));
        var getAllMachinesByProjectIdQuery = new GetAllMachinesByProjectIdQuery(project);
        var machines = machineQueryService.handle(getAllMachinesByProjectIdQuery);
        var machineResources = machines.stream().map(MachineResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(machineResources);
    }

    @PutMapping("/{machineId}")
    public ResponseEntity<MachineResource> updateMachine(@PathVariable Long machineId, @RequestBody UpdateMachineResource updateMachineResource) {
        var updateMachineCommand = UpdateMachineCommandFromResourceAssembler.toCommandFromResource(machineId, updateMachineResource);
        var updateMachine = machineCommandService.handle(updateMachineCommand);
        if (updateMachine.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var machineResource = MachineResourceFromEntityAssembler.toResourceFromEntity(updateMachine.get());
        return ResponseEntity.ok(machineResource);
    }

    @DeleteMapping("/{machineId}")
    public ResponseEntity<?> deleteMachine(@PathVariable Long machineId) {
        var deleteMachineCommand = new DeleteMachineCommand(machineId);
        machineCommandService.handle(deleteMachineCommand);
        return ResponseEntity.ok("Machine deleted successfully");
    }
}

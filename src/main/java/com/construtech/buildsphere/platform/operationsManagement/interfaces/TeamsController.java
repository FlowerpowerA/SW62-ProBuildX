package com.construtech.buildsphere.platform.operationsManagement.interfaces;


import com.construtech.buildsphere.platform.operationsManagement.domain.model.commands.DeleteTeamCommand;
import com.construtech.buildsphere.platform.operationsManagement.domain.model.queries.GetAllTaskByTeamIdQuery;
import com.construtech.buildsphere.platform.operationsManagement.domain.model.queries.GetAllTeamsByProjectIdQuery;
import com.construtech.buildsphere.platform.operationsManagement.domain.model.queries.GetTeamByIdQuery;
import com.construtech.buildsphere.platform.operationsManagement.domain.model.valueobjects.Project;
import com.construtech.buildsphere.platform.operationsManagement.domain.services.TaskQueryService;
import com.construtech.buildsphere.platform.operationsManagement.domain.services.TeamCommandService;
import com.construtech.buildsphere.platform.operationsManagement.domain.services.TeamQueryService;
import com.construtech.buildsphere.platform.operationsManagement.interfaces.resources.CreateTeamResource;
import com.construtech.buildsphere.platform.operationsManagement.interfaces.resources.TaskResource;
import com.construtech.buildsphere.platform.operationsManagement.interfaces.resources.TeamResource;
import com.construtech.buildsphere.platform.operationsManagement.interfaces.resources.UpdateTeamResource;
import com.construtech.buildsphere.platform.operationsManagement.interfaces.transform.CreateTeamCommandFromResourceAssembler;
import com.construtech.buildsphere.platform.operationsManagement.interfaces.transform.TaskResourceFromEntityAssembler;
import com.construtech.buildsphere.platform.operationsManagement.interfaces.transform.TeamResourceFromEntityAssembler;
import com.construtech.buildsphere.platform.operationsManagement.interfaces.transform.UpdateTeamCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/teams", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Teams", description = "Teams Management Endpoints")
public class TeamsController {

    private final TeamCommandService teamCommandService;
    private final TeamQueryService teamQueryService;
    private final TaskQueryService taskQueryService;

    public TeamsController(TeamCommandService teamCommandService, TeamQueryService teamQueryService, TaskQueryService taskQueryService){
        this.teamCommandService = teamCommandService;
        this.teamQueryService = teamQueryService;
        this.taskQueryService = taskQueryService;
    }

    @PostMapping
    public ResponseEntity<TeamResource> createTeam(@RequestBody CreateTeamResource createTeamResource){
        var createTeamCommand = CreateTeamCommandFromResourceAssembler.toCommandFromResource(createTeamResource);
        var teamId = teamCommandService.handle(createTeamCommand);
        if (teamId == 0L){
            return ResponseEntity.badRequest().build();
        }
        var getTeamByIdQuery = new GetTeamByIdQuery(teamId);
        var team = teamQueryService.handle(getTeamByIdQuery);
        if (team.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        var teamResource = TeamResourceFromEntityAssembler.toResourceFromEntity(team.get());
        return  new ResponseEntity<>(teamResource, HttpStatus.CREATED);
    }

    @GetMapping("/{teamId}")
    public ResponseEntity<TeamResource> getTeam(@PathVariable Long teamId){
        var getTeamByIdQuery = new GetTeamByIdQuery(teamId);
        var team = teamQueryService.handle(getTeamByIdQuery);
        if(team.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var teamResource = TeamResourceFromEntityAssembler.toResourceFromEntity(team.get());
        return ResponseEntity.ok(teamResource);
    }

    @PutMapping("/{teamId}")
    public ResponseEntity<TeamResource> updateTeam(@PathVariable Long teamId, @RequestBody UpdateTeamResource updateTeamResource){
        var updateTeamCommand = UpdateTeamCommandFromResourceAssembler.toCommandFromResource(teamId, updateTeamResource);
        var updateTeam = teamCommandService.handle(updateTeamCommand);
        if (updateTeam.isEmpty()){
            return  ResponseEntity.badRequest().build();
        }
        var teamResource = TeamResourceFromEntityAssembler.toResourceFromEntity(updateTeam.get());
        return ResponseEntity.ok(teamResource);
    }

    @GetMapping("/{teamId}/tasks")
    public ResponseEntity<List<TaskResource>> getAllTaskByTeamId(@PathVariable Long teamId){
        var getAllTasksByTeamIdQuery = new GetAllTaskByTeamIdQuery(teamId);
        var tasks = taskQueryService.handle(getAllTasksByTeamIdQuery);
        var taskResources = tasks.stream().map(TaskResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(taskResources);
    }

    @DeleteMapping("/{teamId}")
    public ResponseEntity<?> deleteTeam(@PathVariable Long teamId){
        var deleteTeamCommand = new DeleteTeamCommand(teamId);
        teamCommandService.handle(deleteTeamCommand);
        return ResponseEntity.ok("Team deleted successfully");
    }

}

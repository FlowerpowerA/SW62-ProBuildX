package com.construtech.buildsphere.platform.operationsManagement.application.internal.commandservices;

import com.construtech.buildsphere.platform.operationsManagement.domain.model.aggregates.Team;
import com.construtech.buildsphere.platform.operationsManagement.domain.model.commands.CreateTeamCommand;
import com.construtech.buildsphere.platform.operationsManagement.domain.model.commands.DeleteTeamCommand;
import com.construtech.buildsphere.platform.operationsManagement.domain.model.commands.UpdateTeamCommand;
import com.construtech.buildsphere.platform.operationsManagement.domain.model.valueobjects.Project;
import com.construtech.buildsphere.platform.operationsManagement.domain.services.TeamCommandService;
import com.construtech.buildsphere.platform.operationsManagement.infrastructure.persistence.jpa.repositories.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeamCommandServiceImpl implements TeamCommandService {
    private final TeamRepository teamRepository;

    public TeamCommandServiceImpl(TeamRepository teamRepository){
        this.teamRepository = teamRepository;
    }

    @Override
    public Long handle(CreateTeamCommand command){
        var projectId = new Project(command.project());
        if (teamRepository.existsByTeamNameAndProject(command.teamName(), projectId)){
            throw new IllegalArgumentException("Team with the same name already exists in the projectRM");
        }
        var team = new Team(command);
        try {
            teamRepository.save(team);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving team: " + e.getMessage());
        }
        return team.getId();
    }

    @Override
    public Optional<Team> handle(UpdateTeamCommand command){
        if (teamRepository.existsByTeamNameAndIdIsNot(command.teamName(), command.id())){
            throw new IllegalArgumentException("Team with same team name already exists");
        }
        var result = teamRepository.findById(command.id());
        if (result.isEmpty()) {
            throw new IllegalArgumentException("Team does not exists");
        }
        var teamToUpdate = result.get();
        try{
            var updatedTeam = teamRepository.save(teamToUpdate.updateInformation(command.teamName(), command.description()));
            return Optional.of(updatedTeam);
        } catch (Exception e){
            throw new IllegalArgumentException("Error while updating team: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteTeamCommand command){
        if(!teamRepository.existsById(command.teamId())){
            throw new IllegalArgumentException("Team does not exist");
        }
        try{
            teamRepository.deleteById(command.teamId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting team: " + e.getMessage());
        }
    }
}

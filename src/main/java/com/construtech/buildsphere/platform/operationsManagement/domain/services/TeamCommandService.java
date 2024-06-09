package com.construtech.buildsphere.platform.operationsManagement.domain.services;

import com.construtech.buildsphere.platform.operationsManagement.domain.model.aggregates.Team;
import com.construtech.buildsphere.platform.operationsManagement.domain.model.commands.CreateTeamCommand;
import com.construtech.buildsphere.platform.operationsManagement.domain.model.commands.DeleteTeamCommand;
import com.construtech.buildsphere.platform.operationsManagement.domain.model.commands.UpdateTeamCommand;

import java.util.Optional;

public interface TeamCommandService {
    Long handle(CreateTeamCommand command);
    Optional<Team> handle(UpdateTeamCommand command);
    void handle(DeleteTeamCommand command);
}

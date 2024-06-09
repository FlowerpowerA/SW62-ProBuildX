package com.construtech.buildsphere.platform.operationsManagement.interfaces.transform;

import com.construtech.buildsphere.platform.operationsManagement.domain.model.commands.UpdateTeamCommand;
import com.construtech.buildsphere.platform.operationsManagement.interfaces.resources.UpdateTeamResource;

public class UpdateTeamCommandFromResourceAssembler {
    public static UpdateTeamCommand toCommandFromResource(Long teamId, UpdateTeamResource resource){
        return new UpdateTeamCommand(teamId,resource.teamName(), resource.description());
    }
}

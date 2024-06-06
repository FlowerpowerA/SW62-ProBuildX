package com.construtech.buildsphere.platform.operationsManagement.interfaces.transform;

import com.construtech.buildsphere.platform.operationsManagement.domain.model.commands.CreateTeamCommand;
import com.construtech.buildsphere.platform.operationsManagement.interfaces.resources.CreateTeamResource;

public class CreateTeamCommandFromResourceAssembler {

    public static CreateTeamCommand toCommandFromResource(CreateTeamResource resource){
        return new CreateTeamCommand(resource.teamName(), resource.description(), resource.projectId());
    }
}

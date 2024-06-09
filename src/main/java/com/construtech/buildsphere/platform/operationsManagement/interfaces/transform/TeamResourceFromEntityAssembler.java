package com.construtech.buildsphere.platform.operationsManagement.interfaces.transform;

import com.construtech.buildsphere.platform.operationsManagement.domain.model.aggregates.Team;
import com.construtech.buildsphere.platform.operationsManagement.interfaces.resources.TeamResource;

public class TeamResourceFromEntityAssembler {

    public static TeamResource toResourceFromEntity(Team entity){
        return new TeamResource(entity.getId(), entity.getTeamName(), entity.getDescription(), entity.getProjectId());
    }
}

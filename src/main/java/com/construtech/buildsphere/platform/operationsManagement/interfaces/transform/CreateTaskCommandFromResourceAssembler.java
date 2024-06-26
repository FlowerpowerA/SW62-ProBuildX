package com.construtech.buildsphere.platform.operationsManagement.interfaces.transform;

import com.construtech.buildsphere.platform.operationsManagement.domain.model.commands.CreateTaskCommand;
import com.construtech.buildsphere.platform.operationsManagement.interfaces.resources.CreateTaskResource;

public class CreateTaskCommandFromResourceAssembler {

    public static CreateTaskCommand toCommandFromResource(CreateTaskResource resource){
        return new CreateTaskCommand(resource.taskName(), resource.taskDescription(), resource.startDate(), resource.maxEndDate(), resource.teamId(), resource.projectId());
    }
}

package com.construtech.buildsphere.platform.operationsManagement.interfaces.transform;

import com.construtech.buildsphere.platform.operationsManagement.domain.model.commands.UpdateTaskCommand;
import com.construtech.buildsphere.platform.operationsManagement.interfaces.resources.UpdateTaskResource;

public class UpdateTaskCommandFromResourceAssembler {

    public static UpdateTaskCommand toCommandFromResource(Long taskId, UpdateTaskResource resource){
        return new UpdateTaskCommand(taskId, resource.taskName(), resource.taskDescription(), resource.startDate(), resource.maxEndDate(), resource.teamId());
    }
}

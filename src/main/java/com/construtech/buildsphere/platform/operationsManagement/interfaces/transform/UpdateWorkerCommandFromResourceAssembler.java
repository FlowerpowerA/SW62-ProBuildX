package com.construtech.buildsphere.platform.operationsManagement.interfaces.transform;

import com.construtech.buildsphere.platform.operationsManagement.domain.model.commands.UpdateWorkerCommand;
import com.construtech.buildsphere.platform.operationsManagement.interfaces.resources.UpdateWorkerResource;

public class UpdateWorkerCommandFromResourceAssembler {
    public static UpdateWorkerCommand toCommandFromResource(Long workerId, UpdateWorkerResource resource){
        return new UpdateWorkerCommand(workerId, resource.fullName(), resource.role(), resource.hoursWorked(), resource.teamId());
    }
}

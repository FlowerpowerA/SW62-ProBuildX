package com.construtech.buildsphere.platform.operationsManagement.interfaces.transform;

import com.construtech.buildsphere.platform.operationsManagement.domain.model.commands.CreateWorkerCommand;
import com.construtech.buildsphere.platform.operationsManagement.interfaces.resources.CreateWorkerResource;

public class CreateWorkerCommandFromResourceAssembler {

    public static CreateWorkerCommand toCommandFromResource(CreateWorkerResource resource){
        return new CreateWorkerCommand(resource.fullName(), resource.role(), resource.hoursWorked(), resource.teamId(), resource.projectId());
    }
}

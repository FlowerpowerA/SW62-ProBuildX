package com.construtech.buildsphere.platform.resourceManagement.interfaces.rest.transform;

import com.construtech.buildsphere.platform.resourceManagement.domain.model.commands.CreateMachineCommand;
import com.construtech.buildsphere.platform.resourceManagement.interfaces.rest.resources.CreateMachineResource;

public class CreateMachineCommandFromResourceAssembler {
    public static CreateMachineCommand toCommandFromResource(CreateMachineResource resource) {
        return new CreateMachineCommand(resource.machineName(), resource.description()
        , resource.receptionDate(), resource.endDate(), resource.totalCost(), resource.projectId());
    }

}

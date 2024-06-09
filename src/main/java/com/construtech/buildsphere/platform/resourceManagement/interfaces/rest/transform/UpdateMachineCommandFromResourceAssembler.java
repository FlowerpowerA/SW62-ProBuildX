package com.construtech.buildsphere.platform.resourceManagement.interfaces.rest.transform;

import com.construtech.buildsphere.platform.resourceManagement.domain.model.commands.UpdateMachineCommand;
import com.construtech.buildsphere.platform.resourceManagement.interfaces.rest.resources.UpdateMachineResource;

public class UpdateMachineCommandFromResourceAssembler {
    public static UpdateMachineCommand toCommandFromResource(Long id, UpdateMachineResource resource) {
        return new UpdateMachineCommand(id, resource.machineName(), resource.description(),
                resource.endDate(), resource.totalCost());
    }
}

package com.construtech.buildsphere.platform.resourceManagement.interfaces.rest.transform;

import com.construtech.buildsphere.platform.resourceManagement.domain.model.aggregates.Machine;
import com.construtech.buildsphere.platform.resourceManagement.interfaces.rest.resources.MachineResource;

public class MachineResourceFromEntityAssembler {
    public static MachineResource toResourceFromEntity(Machine entity) {
        return new MachineResource(entity.getId(), entity.getMachineName(),
                entity.getDescription(), entity.getReceptionDate(), entity.getEndDate()
        ,entity.getTotalCost(), entity.getProject());
    }
}

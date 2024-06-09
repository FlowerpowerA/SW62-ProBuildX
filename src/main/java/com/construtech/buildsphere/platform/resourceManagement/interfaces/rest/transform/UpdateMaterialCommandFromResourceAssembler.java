package com.construtech.buildsphere.platform.resourceManagement.interfaces.rest.transform;

import com.construtech.buildsphere.platform.resourceManagement.domain.model.commands.UpdateMaterialCommand;
import com.construtech.buildsphere.platform.resourceManagement.interfaces.rest.resources.UpdateMaterialResource;

public class UpdateMaterialCommandFromResourceAssembler {
    public static UpdateMaterialCommand toCommandFromResource(Long id, UpdateMaterialResource resource) {
        return new UpdateMaterialCommand(id, resource.materialName(), resource.description(),
                resource.amount(), resource.totalCost(), resource.status());
    }

}

package com.construtech.buildsphere.platform.resourceManagement.interfaces.rest.transform;

import com.construtech.buildsphere.platform.resourceManagement.domain.model.commands.CreateMaterialCommand;
import com.construtech.buildsphere.platform.resourceManagement.interfaces.rest.resources.CreateMaterialResource;

public class CreateMaterialCommandFromResourceAssembler {
    public static CreateMaterialCommand toCommandFromResource(CreateMaterialResource resource) {
      return new CreateMaterialCommand(resource.materialName(), resource.description(),
              resource.receptionDate(), resource.amount(), resource.totalCost(), resource.status(), resource.projectId());
    }
}

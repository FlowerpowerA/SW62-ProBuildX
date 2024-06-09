package com.construtech.buildsphere.platform.resourceManagement.domain.services;

import com.construtech.buildsphere.platform.resourceManagement.domain.model.aggregates.Material;
import com.construtech.buildsphere.platform.resourceManagement.domain.model.commands.CreateMaterialCommand;
import com.construtech.buildsphere.platform.resourceManagement.domain.model.commands.DeleteMaterialCommand;
import com.construtech.buildsphere.platform.resourceManagement.domain.model.commands.UpdateMaterialCommand;

import java.util.Optional;

public interface MaterialCommandService {
    Long handle(CreateMaterialCommand command);
    Optional<Material> handle(UpdateMaterialCommand command);
    void handle(DeleteMaterialCommand command);
}

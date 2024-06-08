package com.construtech.buildsphere.platform.resourceManagement.domain.services;

import com.construtech.buildsphere.platform.resourceManagement.domain.model.aggregates.Material;
import com.construtech.buildsphere.platform.resourceManagement.domain.model.commands.CreateMaterialCommand;

import java.util.Optional;

public interface MaterialCommandService {
    Optional<Material> handle(CreateMaterialCommand command);
}

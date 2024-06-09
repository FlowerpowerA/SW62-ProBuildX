package com.construtech.buildsphere.platform.resourceManagement.interfaces.rest.transform;

import com.construtech.buildsphere.platform.resourceManagement.domain.model.aggregates.Material;
import com.construtech.buildsphere.platform.resourceManagement.interfaces.rest.resources.MaterialResource;

public class MaterialResourceFromEntityAssembler {
    public static MaterialResource toResourceFromEntity(Material entity) {
        return new MaterialResource(entity.getId(), entity.getMaterialName(),
                entity.getDescription(), entity.getReceptionDate(),
                entity.getAmount(), entity.getTotalCost(), entity.getStatus(), entity.getProjectId());
    }
}

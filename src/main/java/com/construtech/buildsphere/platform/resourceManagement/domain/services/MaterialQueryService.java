package com.construtech.buildsphere.platform.resourceManagement.domain.services;

import com.construtech.buildsphere.platform.resourceManagement.domain.model.aggregates.Material;
import com.construtech.buildsphere.platform.resourceManagement.domain.model.queries.GetAllMaterialsByProjectIdQuery;
import com.construtech.buildsphere.platform.resourceManagement.domain.model.queries.GetMaterialByIdQuery;

import java.util.List;
import java.util.Optional;

public interface MaterialQueryService {
    Optional<Material> handle(GetMaterialByIdQuery query);
    List<Material> handle(GetAllMaterialsByProjectIdQuery query);
}

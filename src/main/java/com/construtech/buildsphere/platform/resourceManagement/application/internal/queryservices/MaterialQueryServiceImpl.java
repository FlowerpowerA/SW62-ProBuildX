package com.construtech.buildsphere.platform.resourceManagement.application.internal.queryservices;

import com.construtech.buildsphere.platform.resourceManagement.domain.model.aggregates.Material;
import com.construtech.buildsphere.platform.resourceManagement.domain.model.queries.GetAllMaterialsByProjectIdQuery;
import com.construtech.buildsphere.platform.resourceManagement.domain.model.queries.GetMaterialByIdQuery;
import com.construtech.buildsphere.platform.resourceManagement.domain.services.MaterialQueryService;
import com.construtech.buildsphere.platform.resourceManagement.infrastructure.persistence.jpa.repositories.MaterialRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaterialQueryServiceImpl implements MaterialQueryService {
    private final MaterialRepository materialRepository;

    public MaterialQueryServiceImpl(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    @Override
    public Optional<Material> handle(GetMaterialByIdQuery query) {
        return materialRepository.findById(query.materialId());
    }

    @Override
    public List<Material> handle(GetAllMaterialsByProjectIdQuery query) {
        return materialRepository.findAllByProject(query.project());
    }
}

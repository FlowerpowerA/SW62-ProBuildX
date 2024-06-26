package com.construtech.buildsphere.platform.resourceManagement.application.internal.commandservices;

import com.construtech.buildsphere.platform.resourceManagement.domain.model.aggregates.Material;
import com.construtech.buildsphere.platform.resourceManagement.domain.model.commands.CreateMaterialCommand;
import com.construtech.buildsphere.platform.resourceManagement.domain.model.commands.DeleteMaterialCommand;
import com.construtech.buildsphere.platform.resourceManagement.domain.model.commands.UpdateMaterialCommand;
import com.construtech.buildsphere.platform.resourceManagement.domain.model.valueobjects.ProjectRM;
import com.construtech.buildsphere.platform.resourceManagement.domain.services.MaterialCommandService;
import com.construtech.buildsphere.platform.resourceManagement.infrastructure.persistence.jpa.repositories.MaterialRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MaterialCommandServiceImpl implements MaterialCommandService {
    private final MaterialRepository materialRepository;

    public MaterialCommandServiceImpl(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }


    @Override
    public Long handle(CreateMaterialCommand command) {
        var projectId = new ProjectRM(command.project());

        if(materialRepository.existsByMaterialNameAndProject(command.materialName(), projectId)) {
            throw new IllegalArgumentException("Material with name " +
                    command.materialName() + " already exists in the projectRM");
        }

        var material = new Material(command);

        try {
            materialRepository.save(material);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving material" +
                    e.getMessage());
        }
        return material.getId();
    }

    @Override
    public Optional<Material> handle(UpdateMaterialCommand command) {
        if (materialRepository.existsByMaterialNameAndIdNot(command.materialName(), command.id())) {
            throw new IllegalArgumentException("Material with the same name already exists in the projectRM");
        }

        var result = materialRepository.findById(command.id());
        if (result.isEmpty()) {
            throw new IllegalArgumentException("Material not found");
        }

        var materialToUpdate = result.get();

        try {
            var updatedMaterial = materialRepository.save(materialToUpdate.updateMaterial(command.materialName()
                    , command.description(), command.amount(), command.totalCost(), command.materialStatus()));
            return Optional.of(updatedMaterial);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating material: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteMaterialCommand command) {
        if (!materialRepository.existsById(command.materialId())) {
            throw new IllegalArgumentException("Material not found");
        }
        try {
            materialRepository.deleteById(command.materialId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting material: " + e.getMessage());
        }
    }
}

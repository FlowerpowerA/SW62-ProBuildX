package com.construtech.buildsphere.platform.resourceManagement.domain.model.commands;

public record DeleteMaterialCommand(String materialName, String description, String receptionDate,
                                    int amount, double totalCost, String status, Long projectId) {
    public DeleteMaterialCommand {
        if (projectId <= 0) {
            throw new IllegalArgumentException("Project ID must be greater than 0");
        }
    }

}

package com.construtech.buildsphere.platform.resourceManagement.domain.model.commands;

public record UpdateMaterialCommand(String materialName, String description, int amount, double totalCost,
                                    String status, Long projectId) {
    public UpdateMaterialCommand {
        if (projectId <= 0) {
            throw new IllegalArgumentException("Project ID must be greater than 0");
        }

        if (materialName == null || materialName.isBlank()) {
            throw new IllegalArgumentException("Material name cannot be null or empty");
        }

        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Material description cannot be null or empty");
        }

        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }

        if (totalCost <= 0) {
            throw new IllegalArgumentException("Total cost must be greater than 0");
        }

        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("Status cannot be null or empty");
        }
    }
}

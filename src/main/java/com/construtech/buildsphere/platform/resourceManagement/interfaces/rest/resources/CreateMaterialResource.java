package com.construtech.buildsphere.platform.resourceManagement.interfaces.rest.resources;

public record CreateMaterialResource(
        String materialName,
        String description,
        String receptionDate,
        int amount,
        double totalCost,
        String status,
        int projectId
) {
}

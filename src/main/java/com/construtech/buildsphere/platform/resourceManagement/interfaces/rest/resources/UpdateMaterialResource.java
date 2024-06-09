package com.construtech.buildsphere.platform.resourceManagement.interfaces.rest.resources;

public record UpdateMaterialResource(
        String materialName,
        String description,
        int amount,
        double totalCost,
        String status
) {
}

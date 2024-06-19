package com.construtech.buildsphere.platform.resourceManagement.interfaces.rest.resources;

public record CreateMachineResource(
        String machineName,
        String description,
        String receptionDate,
        String endDate,
        double totalCost,
        Long projectId
) {
}

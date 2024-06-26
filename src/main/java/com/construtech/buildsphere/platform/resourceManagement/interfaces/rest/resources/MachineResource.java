package com.construtech.buildsphere.platform.resourceManagement.interfaces.rest.resources;

public record MachineResource(
        Long id,
        String machineName,
        String description,
        String receptionDate,
        String endDate,
        double totalCost,
        Long projectId
) {
}

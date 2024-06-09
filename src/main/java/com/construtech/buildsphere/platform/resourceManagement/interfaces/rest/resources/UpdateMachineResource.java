package com.construtech.buildsphere.platform.resourceManagement.interfaces.rest.resources;

public record UpdateMachineResource(
        String machineName,
        String description,
        String endDate,
        double totalCost
) {
}

package com.construtech.buildsphere.platform.resourceManagement.interfaces.rest.resources;

import java.time.LocalDate;

public record MachineResource(
        Long id,
        String machineName,
        String description,
        LocalDate receptionDate,
        LocalDate endDate,
        double totalCost,
        int projectId
) {
}

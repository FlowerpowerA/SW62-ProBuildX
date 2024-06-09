package com.construtech.buildsphere.platform.resourceManagement.interfaces.rest.resources;

import java.time.LocalDate;

public record MaterialResource(
        Long id,
        String machineName,
        String description,
        LocalDate receptionDate,
        int amount,
        double totalCost,
        String status,
        int projectId
) {
}

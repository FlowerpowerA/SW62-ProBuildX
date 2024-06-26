package com.construtech.buildsphere.platform.resourceManagement.interfaces.rest.resources;

import java.time.LocalDate;

public record MaterialResource(
        Long id,
        String materialName,
        String description,
        String receptionDate,
        int amount,
        double totalCost,
        String materialStatus,
        Long projectId
) {
}

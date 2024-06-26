package com.construtech.buildsphere.platform.operationsManagement.interfaces.resources;

public record TaskResource(
        Long id,
        String taskName,
        String taskDescription,
        String startDate,
        String maxEndDate,
        Long teamId,
        Long projectId
) {
}

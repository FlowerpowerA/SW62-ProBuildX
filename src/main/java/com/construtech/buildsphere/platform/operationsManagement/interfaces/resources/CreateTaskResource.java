package com.construtech.buildsphere.platform.operationsManagement.interfaces.resources;

public record CreateTaskResource(
        String taskName,
        String taskDescription,
        String startDate,
        String maxEndDate,
        Long teamId,
        Long projectId
) {
}

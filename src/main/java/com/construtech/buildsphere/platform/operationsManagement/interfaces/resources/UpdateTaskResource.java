package com.construtech.buildsphere.platform.operationsManagement.interfaces.resources;

public record UpdateTaskResource(
        String taskName,
        String taskDescription,
        String startDate,
        String maxEndDate,
        Long teamId
) {
}

package com.construtech.buildsphere.platform.operationsManagement.interfaces.resources;

public record CreateWorkerResource(
        String fullName,
        String role,
        int hoursWorked,
        Long teamId,
        int projectId
) {
}

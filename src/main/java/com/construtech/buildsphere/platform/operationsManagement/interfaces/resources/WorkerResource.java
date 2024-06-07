package com.construtech.buildsphere.platform.operationsManagement.interfaces.resources;

public record WorkerResource(
        Long id,
        String fullName,
        String role,
        int hoursWorked,
        Long teamId,
        int projectId
) {
}

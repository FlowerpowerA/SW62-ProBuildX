package com.construtech.buildsphere.platform.operationsManagement.interfaces.resources;

public record UpdateWorkerResource(
        String fullName,
        String role,
        int hoursWorked,
        Long teamId
) {
}

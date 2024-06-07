package com.construtech.buildsphere.platform.operationsManagement.domain.model.commands;

public record CreateWorkerCommand(String fullName, String role, int hoursWorked, Long teamId, int project) {
}

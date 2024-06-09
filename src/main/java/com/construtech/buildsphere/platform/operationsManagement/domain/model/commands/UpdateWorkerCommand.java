package com.construtech.buildsphere.platform.operationsManagement.domain.model.commands;

public record UpdateWorkerCommand(Long id, String fullName, String role, int hoursWorked, Long team) {
}

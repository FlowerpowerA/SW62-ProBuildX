package com.construtech.buildsphere.platform.operationsManagement.domain.model.commands;

public record UpdateTaskCommand(Long id, String taskName, String taskDescription, String startDate, String maxEndDate, Long team) {
}

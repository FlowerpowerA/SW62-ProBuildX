package com.construtech.buildsphere.platform.operationsManagement.domain.model.commands;

public record CreateTaskCommand(String taskName, String taskDescription, String startDate, String maxEndDate, Long teamId, int project){
}

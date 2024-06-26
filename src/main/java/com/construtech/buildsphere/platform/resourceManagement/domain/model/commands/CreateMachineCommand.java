package com.construtech.buildsphere.platform.resourceManagement.domain.model.commands;

public record CreateMachineCommand(String machineName, String description, String receptionDate, String endDate,
                                   double totalCost, Long project) {
}

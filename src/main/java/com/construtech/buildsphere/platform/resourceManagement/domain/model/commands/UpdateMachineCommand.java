package com.construtech.buildsphere.platform.resourceManagement.domain.model.commands;

public record UpdateMachineCommand(Long id, String machineName, String description, String endDate,
                                   double totalCost) {
}

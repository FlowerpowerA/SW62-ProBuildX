package com.construtech.buildsphere.platform.resourceManagement.domain.model.commands;

public record DeleteMachineCommand(String machineName, String description, String receptionDate, String endDate,
                                   double totalCost, String status, Long projectId) {

    public DeleteMachineCommand {
        if (projectId <= 0) {
            throw new IllegalArgumentException("Project ID must be greater than 0");
        }
    }
}

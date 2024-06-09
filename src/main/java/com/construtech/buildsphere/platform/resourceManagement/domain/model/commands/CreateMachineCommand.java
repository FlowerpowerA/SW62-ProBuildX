package com.construtech.buildsphere.platform.resourceManagement.domain.model.commands;

public record CreateMachineCommand(String machineName, String description, String receptionDate, String endDate,
                                   double totalCost, Long projectId) {
    public CreateMachineCommand {
        if (projectId <= 0) {
            throw new IllegalArgumentException("Project ID must be greater than 0");
        }

        if (machineName == null || machineName.isBlank()) {
            throw new IllegalArgumentException("Machine name cannot be null or empty");
        }

        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Machine description cannot be null or empty");
        }

        if (receptionDate == null || receptionDate.isBlank()) {
            throw new IllegalArgumentException("Reception date cannot be null or empty");
        }

        if (totalCost <= 0) {
            throw new IllegalArgumentException("Total cost must be greater than 0");
        }
    }
}

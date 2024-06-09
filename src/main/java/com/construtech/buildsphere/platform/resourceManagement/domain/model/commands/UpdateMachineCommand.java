package com.construtech.buildsphere.platform.resourceManagement.domain.model.commands;

public record UpdateMachineCommand(String machineName, String description, String endDate,
                                   double totalCost, String status) {
    public UpdateMachineCommand {
        if (machineName == null || machineName.isBlank()) {
            throw new IllegalArgumentException("Machine name cannot be null or empty");
        }

        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Machine description cannot be null or empty");
        }

        if (totalCost <= 0) {
            throw new IllegalArgumentException("Total cost must be greater than 0");
        }

        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("Status cannot be null or empty");
        }
    }
}

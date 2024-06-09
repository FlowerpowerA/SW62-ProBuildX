package com.construtech.buildsphere.platform.resourceManagement.domain.model.commands;

public record UpdateMachineCommand(Long id, String machineName, String description, String endDate,
                                   double totalCost) {
    public UpdateMachineCommand {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Id must be greater than 0");
        }

        if (machineName == null || machineName.isBlank()) {
            throw new IllegalArgumentException("Machine name cannot be null or empty");
        }

        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Machine description cannot be null or empty");
        }

        if (totalCost <= 0) {
            throw new IllegalArgumentException("Total cost must be greater than 0");
        }
    }
}

package com.construtech.buildsphere.platform.resourceManagement.domain.model.commands;

public record UpdateMaterialCommand(Long id, String materialName, String description, int amount, double totalCost,
                                    String materialStatus) {
}


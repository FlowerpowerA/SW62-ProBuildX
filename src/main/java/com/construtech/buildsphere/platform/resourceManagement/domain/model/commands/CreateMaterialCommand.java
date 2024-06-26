package com.construtech.buildsphere.platform.resourceManagement.domain.model.commands;

public record CreateMaterialCommand(String materialName, String description, String receptionDate,
                                    int amount, double totalCost, String materialStatus, Long project) {
}

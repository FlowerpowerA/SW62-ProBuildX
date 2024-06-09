package com.construtech.buildsphere.platform.operationsManagement.domain.model.commands;

public record UpdateTeamCommand(Long id, String teamName, String description) {
}

package com.construtech.buildsphere.platform.operationsManagement.domain.model.commands;

public record CreateTeamCommand(String teamName, String description, Long project) {
}

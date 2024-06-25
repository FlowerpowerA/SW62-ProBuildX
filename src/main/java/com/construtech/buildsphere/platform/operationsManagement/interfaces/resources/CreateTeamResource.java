package com.construtech.buildsphere.platform.operationsManagement.interfaces.resources;

public record CreateTeamResource(
        String teamName,
        String description,
        Long projectId
) {
}

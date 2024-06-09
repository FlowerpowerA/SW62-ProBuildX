package com.construtech.buildsphere.platform.operationsManagement.interfaces.resources;

public record TeamResource(
        Long id,
        String teamName,
        String description,
        int projectId
) {
}

package com.construtech.buildsphere.platform.project_management.interfaces.rest.resources;

public record CreateProjectResource(
        String name,
        String description,
        String location,
        String startDate,
        String expectedEndDate,
        String budget,
        String urlImage,
        Long userId) {
}

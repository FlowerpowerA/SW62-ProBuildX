package com.construtech.buildsphere.platform.project_management.interfaces.rest.resources;

public record ProjectResource(
        Long id,
        String name,
        String description,
        String location,
        String startDate,
        String expectedEndDate,
        String budget,
        String urlImage) {
}

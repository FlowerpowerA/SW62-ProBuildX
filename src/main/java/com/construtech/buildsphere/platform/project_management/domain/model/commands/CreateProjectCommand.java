package com.construtech.buildsphere.platform.project_management.domain.model.commands;

public record CreateProjectCommand(String name, String description, String location, String startDate, String expectedEndDate, String budget, String urlImage, Long userId) {
}

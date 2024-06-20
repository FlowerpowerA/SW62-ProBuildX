package com.construtech.buildsphere.platform.project_management.domain.services;

import com.construtech.buildsphere.platform.project_management.domain.model.aggregates.Dashboard;
import com.construtech.buildsphere.platform.project_management.domain.model.commands.CreateProjectCommand;
import com.construtech.buildsphere.platform.project_management.domain.model.commands.UpdateProjectCommand;
import com.construtech.buildsphere.platform.project_management.domain.model.commands.DeleteProjectCommand;

import java.util.Optional;

public interface ProjectCommandService {
    Optional<Dashboard> handle(CreateProjectCommand command);
    Optional<Dashboard> handle(UpdateProjectCommand command);
    void handle(DeleteProjectCommand command);
}


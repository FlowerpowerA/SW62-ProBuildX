package com.construtech.buildsphere.platform.project_management.interfaces.rest.transform;

import com.construtech.buildsphere.platform.project_management.domain.model.commands.CreateProjectCommand;
import com.construtech.buildsphere.platform.project_management.interfaces.rest.resources.CreateProjectResource;

public class CreateProjectCommandFromResourceAssembler {
    public static CreateProjectCommand toCommandFromResource(CreateProjectResource resource) {
        return new CreateProjectCommand(
                resource.name(),
                resource.description(),
                resource.location(),
                resource.startDate(),
                resource.expectedEndDate(),
                resource.budget(),
                resource.urlImage(),
                resource.userId()
        );
    }
}

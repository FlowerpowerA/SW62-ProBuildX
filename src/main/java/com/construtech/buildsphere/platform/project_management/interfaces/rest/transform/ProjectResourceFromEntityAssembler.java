package com.construtech.buildsphere.platform.project_management.interfaces.rest.transform;

import com.construtech.buildsphere.platform.project_management.domain.model.entities.Project;
import com.construtech.buildsphere.platform.project_management.interfaces.rest.resources.ProjectResource;

public class ProjectResourceFromEntityAssembler {
    public static ProjectResource toResourceFromEntity(Project entity) {
        return new ProjectResource(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getLocation(),
                entity.getStartDate(),
                entity.getExpectedEndDate(),
                entity.getBudget(),
                entity.getUrlImage()
        );
    }
}

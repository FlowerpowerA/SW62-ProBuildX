package com.construtech.buildsphere.platform.resourceManagement.domain.model.queries;

import com.construtech.buildsphere.platform.resourceManagement.domain.model.valueobjects.ProjectId;

public record GetAllMaterialsByProjectIdQuery(ProjectId projectId) {
}

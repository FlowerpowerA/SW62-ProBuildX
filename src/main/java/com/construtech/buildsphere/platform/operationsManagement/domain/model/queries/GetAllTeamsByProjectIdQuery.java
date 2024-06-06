package com.construtech.buildsphere.platform.operationsManagement.domain.model.queries;

import com.construtech.buildsphere.platform.operationsManagement.domain.model.valueobjects.ProjectId;

public record GetAllTeamsByProjectIdQuery(ProjectId projectId) {
}

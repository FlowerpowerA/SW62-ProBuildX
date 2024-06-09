package com.construtech.buildsphere.platform.operationsManagement.domain.model.queries;

import com.construtech.buildsphere.platform.operationsManagement.domain.model.valueobjects.Project;

public record GetAllWorkersByProjectIdQuery(Project project) {
}

package com.construtech.buildsphere.platform.project_management.domain.services;

import com.construtech.buildsphere.platform.project_management.domain.model.aggregates.Dashboard;
import com.construtech.buildsphere.platform.project_management.domain.model.queries.GetAllProjectsQuery;
import com.construtech.buildsphere.platform.project_management.domain.model.queries.GetProjectByIdQuery;

import java.util.List;
import java.util.Optional;

public interface ProjectQueryService {
    List<Dashboard> handle(GetAllProjectsQuery query);
    Optional<Dashboard> handle(GetProjectByIdQuery query);
}

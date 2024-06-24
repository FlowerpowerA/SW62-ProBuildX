package com.construtech.buildsphere.platform.operationsManagement.domain.services;

import com.construtech.buildsphere.platform.operationsManagement.domain.model.aggregates.Task;
import com.construtech.buildsphere.platform.operationsManagement.domain.model.queries.GetAllTaskByProjectIdQuery;
import com.construtech.buildsphere.platform.operationsManagement.domain.model.queries.GetAllTaskByTeamIdQuery;
import com.construtech.buildsphere.platform.operationsManagement.domain.model.queries.GetTaskByIdQuery;

import java.util.List;
import java.util.Optional;

public interface TaskQueryService {

    Optional<Task> handle(GetTaskByIdQuery query);
    List<Task> handle(GetAllTaskByProjectIdQuery query);
    List<Task> handle(GetAllTaskByTeamIdQuery query);
}

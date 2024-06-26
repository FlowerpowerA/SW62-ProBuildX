package com.construtech.buildsphere.platform.operationsManagement.application.internal.queryservices;

import com.construtech.buildsphere.platform.operationsManagement.domain.model.aggregates.Task;
import com.construtech.buildsphere.platform.operationsManagement.domain.model.queries.GetAllTaskByProjectIdQuery;
import com.construtech.buildsphere.platform.operationsManagement.domain.model.queries.GetAllTaskByTeamIdQuery;
import com.construtech.buildsphere.platform.operationsManagement.domain.model.queries.GetTaskByIdQuery;
import com.construtech.buildsphere.platform.operationsManagement.domain.services.TaskQueryService;
import com.construtech.buildsphere.platform.operationsManagement.infrastructure.persistence.jpa.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskQueryServiceImpl implements TaskQueryService {

    private final TaskRepository taskRepository;

    public TaskQueryServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Optional<Task> handle(GetTaskByIdQuery query) {
        return taskRepository.findById(query.taskId());
    }

    @Override
    public List<Task> handle(GetAllTaskByProjectIdQuery query) {
        return taskRepository.findAllByProject(query.project());
    }

    @Override
    public List<Task> handle(GetAllTaskByTeamIdQuery query) {
        return taskRepository.findAllByTeam(query.team());
    }
}

package com.construtech.buildsphere.platform.operationsManagement.domain.services;

import com.construtech.buildsphere.platform.operationsManagement.domain.model.aggregates.Task;
import com.construtech.buildsphere.platform.operationsManagement.domain.model.commands.CreateTaskCommand;
import com.construtech.buildsphere.platform.operationsManagement.domain.model.commands.DeleteTaskCommand;
import com.construtech.buildsphere.platform.operationsManagement.domain.model.commands.UpdateTaskCommand;

import java.util.Optional;

public interface TaskCommandService {

    Long handle(CreateTaskCommand command);
    Optional<Task> handle(UpdateTaskCommand command);
    void handle(DeleteTaskCommand command);
}

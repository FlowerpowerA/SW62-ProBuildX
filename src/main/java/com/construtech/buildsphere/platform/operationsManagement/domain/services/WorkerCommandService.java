package com.construtech.buildsphere.platform.operationsManagement.domain.services;

import com.construtech.buildsphere.platform.operationsManagement.domain.model.aggregates.Worker;
import com.construtech.buildsphere.platform.operationsManagement.domain.model.commands.CreateWorkerCommand;
import com.construtech.buildsphere.platform.operationsManagement.domain.model.commands.DeleteWorkerCommand;
import com.construtech.buildsphere.platform.operationsManagement.domain.model.commands.UpdateWorkerCommand;

import java.util.Optional;

public interface WorkerCommandService {

    Long handle (CreateWorkerCommand command);
    Optional<Worker> handle(UpdateWorkerCommand command);
    void handle(DeleteWorkerCommand command);
}

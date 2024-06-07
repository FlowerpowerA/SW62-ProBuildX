package com.construtech.buildsphere.platform.operationsManagement.domain.services;

import com.construtech.buildsphere.platform.operationsManagement.domain.model.aggregates.Worker;
import com.construtech.buildsphere.platform.operationsManagement.domain.model.queries.GetAllWorkersByProjectIdQuery;
import com.construtech.buildsphere.platform.operationsManagement.domain.model.queries.GetWorkerByIdQuery;

import java.util.List;
import java.util.Optional;

public interface WorkerQueryService {

   Optional<Worker> handle(GetWorkerByIdQuery query);
   List<Worker> handle(GetAllWorkersByProjectIdQuery query);
}

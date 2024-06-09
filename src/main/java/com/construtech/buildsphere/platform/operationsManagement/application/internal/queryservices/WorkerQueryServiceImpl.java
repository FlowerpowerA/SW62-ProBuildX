package com.construtech.buildsphere.platform.operationsManagement.application.internal.queryservices;

import com.construtech.buildsphere.platform.operationsManagement.domain.model.aggregates.Worker;
import com.construtech.buildsphere.platform.operationsManagement.domain.model.queries.GetAllWorkersByProjectIdQuery;
import com.construtech.buildsphere.platform.operationsManagement.domain.model.queries.GetWorkerByIdQuery;
import com.construtech.buildsphere.platform.operationsManagement.domain.services.WorkerQueryService;
import com.construtech.buildsphere.platform.operationsManagement.infrastructure.persistence.jpa.repositories.WorkerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerQueryServiceImpl implements WorkerQueryService {
    private final WorkerRepository workerRepository;

    public WorkerQueryServiceImpl(WorkerRepository workerRepository){
        this.workerRepository = workerRepository;
    }

    @Override
    public Optional<Worker> handle(GetWorkerByIdQuery query){
        return  workerRepository.findById(query.workerId());
    }

    @Override
    public List<Worker> handle(GetAllWorkersByProjectIdQuery query){
        return workerRepository.findAllByProject(query.project());
    }

}

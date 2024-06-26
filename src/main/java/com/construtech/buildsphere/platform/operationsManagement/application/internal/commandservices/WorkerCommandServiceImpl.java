package com.construtech.buildsphere.platform.operationsManagement.application.internal.commandservices;

import com.construtech.buildsphere.platform.operationsManagement.domain.model.aggregates.Worker;
import com.construtech.buildsphere.platform.operationsManagement.domain.model.commands.CreateWorkerCommand;
import com.construtech.buildsphere.platform.operationsManagement.domain.model.commands.DeleteWorkerCommand;
import com.construtech.buildsphere.platform.operationsManagement.domain.model.commands.UpdateWorkerCommand;
import com.construtech.buildsphere.platform.operationsManagement.domain.model.valueobjects.Project;
import com.construtech.buildsphere.platform.operationsManagement.domain.services.WorkerCommandService;
import com.construtech.buildsphere.platform.operationsManagement.infrastructure.persistence.jpa.repositories.WorkerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WorkerCommandServiceImpl implements WorkerCommandService {
    private final WorkerRepository workerRepository;

    public WorkerCommandServiceImpl(WorkerRepository workerRepository){
        this.workerRepository = workerRepository;
    }

    @Override
    public Long handle(CreateWorkerCommand command){
        var projectId = new Project(command.project());
        if (workerRepository.existsByFullNameAndProject(command.fullName(), projectId)){
            throw new IllegalArgumentException("Worker with the same name already exists in the projectRM");
        }
        var worker = new Worker(command);
        try{
            workerRepository.save(worker);
        }catch (Exception e) {
            throw new IllegalArgumentException("Error while saving worker: " + e.getMessage());
        }
        return worker.getId();
    }

    @Override
    public Optional<Worker> handle(UpdateWorkerCommand command){
        if(workerRepository.existsByFullNameAndIdIsNot(command.fullName(), command.id())){
            throw new IllegalArgumentException("Worker with same name already exists");
        }
        var result = workerRepository.findById(command.id());
        if (result.isEmpty()){
            throw new IllegalArgumentException("Worker does not exists");
        }
        var workerToUpdate = result.get();
        try{
            var updatedWorker = workerRepository.save(workerToUpdate.updateInformation(command.fullName(), command.role(), command.hoursWorked(), command.team()));
            return Optional.of(updatedWorker);
        }catch (Exception e){
            throw new IllegalArgumentException("Error while updating worker: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteWorkerCommand command){
        if(!workerRepository.existsById(command.workerId())){
            throw new IllegalArgumentException("Worker does not exist");
        }
        try {
            workerRepository.deleteById(command.workerId());
        }catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting worker: " + e.getMessage());
        }
    }
}

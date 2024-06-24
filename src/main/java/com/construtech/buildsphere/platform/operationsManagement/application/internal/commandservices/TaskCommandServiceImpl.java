package com.construtech.buildsphere.platform.operationsManagement.application.internal.commandservices;

import com.construtech.buildsphere.platform.operationsManagement.domain.model.aggregates.Task;
import com.construtech.buildsphere.platform.operationsManagement.domain.model.commands.CreateTaskCommand;
import com.construtech.buildsphere.platform.operationsManagement.domain.model.commands.DeleteTaskCommand;
import com.construtech.buildsphere.platform.operationsManagement.domain.model.commands.UpdateTaskCommand;
import com.construtech.buildsphere.platform.operationsManagement.domain.model.valueobjects.Project;
import com.construtech.buildsphere.platform.operationsManagement.domain.services.TaskCommandService;
import com.construtech.buildsphere.platform.operationsManagement.infrastructure.persistence.jpa.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskCommandServiceImpl implements TaskCommandService {
    private final TaskRepository taskRepository;

    public TaskCommandServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Long handle(CreateTaskCommand command) {
        var projectId = new Project(command.project());
        if(taskRepository.existsByTaskNameAndProject(command.taskName(), projectId)){
            throw  new IllegalArgumentException("Task with the same name already exists in the project");
        }
        var task = new Task(command);
        try{
            taskRepository.save(task);
        }catch (Exception e){
            throw new IllegalArgumentException("Error while saving task: " + e.getMessage());
        }
        return task.getId();
    }

    @Override
    public Optional<Task> handle(UpdateTaskCommand command) {
        if(taskRepository.existsByTaskNameAndIdIsNot(command.taskName(), command.id())){
            throw new IllegalArgumentException("Task with same name already exists");
        }
        var result = taskRepository.findById(command.id());
        if (result.isEmpty()){
            throw new IllegalArgumentException("Task does not exists");
        }
        var taskToUpdate = result.get();
        try {
            var updatedTask = taskRepository.save(taskToUpdate.updateInformation(command.taskName(),command.taskDescription(),command.startDate(),command.maxEndDate(),command.team()));
            return Optional.of(updatedTask);
        }catch (Exception e){
            throw new IllegalArgumentException("Error while updating task: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteTaskCommand command) {
        if(!taskRepository.existsById(command.taskId())){
            throw new IllegalArgumentException("Task does not exist");
        }
        try {
            taskRepository.deleteById(command.taskId());
        }catch (Exception e){
            throw new IllegalArgumentException("Error while deleting task: " + e.getMessage());
        }
    }
}

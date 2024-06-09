package com.construtech.buildsphere.platform.project_management.application.internal.commandservices;

import com.construtech.buildsphere.platform.project_management.domain.model.aggregates.Dashboard;
import com.construtech.buildsphere.platform.project_management.domain.model.commands.CreateProjectCommand;
import com.construtech.buildsphere.platform.project_management.domain.model.commands.UpdateProjectCommand;
import com.construtech.buildsphere.platform.project_management.domain.model.commands.DeleteProjectCommand;
import com.construtech.buildsphere.platform.project_management.infrastructure.jpa.persistence.ProjectRepository;
import com.construtech.buildsphere.platform.project_management.domain.services.ProjectCommandService;
import com.construtech.buildsphere.platform.project_management.domain.model.entities.Project;
import com.construtech.buildsphere.platform.project_management.domain.model.valueobjects.UserId;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectCommandServiceImpl implements ProjectCommandService {
    private final ProjectRepository projectRepository;

    public ProjectCommandServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Optional<Dashboard> handle(CreateProjectCommand command) {
        Project project = new Project(command.name(), command.description(), command.location(), command.startDate(), command.expectedEndDate(), command.budget(), command.urlImage());
        projectRepository.save(project);
        Dashboard dashboard = new Dashboard(new UserId(command.userId()), project);
        return Optional.of(dashboard);
    }

    @Override
    public Optional<Dashboard> handle(UpdateProjectCommand command) {
        Optional<Project> projectOptional = projectRepository.findById(command.projectId());
        if (projectOptional.isEmpty()) {
            return Optional.empty();
        }
        Project project = projectOptional.get();
        project.updateDetails(command.name(), command.description(), command.location(), command.startDate(), command.expectedEndDate(), command.budget(), command.urlImage());
        projectRepository.save(project);
        return Optional.of(new Dashboard(new UserId(command.userId()), project));
    }

    @Override
    public void handle(DeleteProjectCommand command) {
        projectRepository.deleteById(command.projectId());
    }
}

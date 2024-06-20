package com.construtech.buildsphere.platform.project_management.application.internal.queryservices;

import com.construtech.buildsphere.platform.project_management.domain.model.aggregates.Dashboard;
import com.construtech.buildsphere.platform.project_management.domain.model.queries.GetAllProjectsQuery;
import com.construtech.buildsphere.platform.project_management.domain.model.queries.GetProjectByIdQuery;
import com.construtech.buildsphere.platform.project_management.infrastructure.jpa.persistence.ProjectRepository;
import com.construtech.buildsphere.platform.project_management.domain.services.ProjectQueryService;
import com.construtech.buildsphere.platform.project_management.domain.model.entities.Project;
import com.construtech.buildsphere.platform.project_management.domain.model.valueobjects.UserId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectQueryServiceImpl implements ProjectQueryService {
    private final ProjectRepository projectRepository;

    public ProjectQueryServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Dashboard> handle(GetAllProjectsQuery query) {
        List<Project> projects = projectRepository.findAll();
        return projects.stream()
                .map(project -> new Dashboard(new UserId(project.getId()), project))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Dashboard> handle(GetProjectByIdQuery query) {
        Optional<Project> projectOptional = projectRepository.findById(query.projectId());
        if (projectOptional.isEmpty()) {
            return Optional.empty();
        }
        Project project = projectOptional.get();
        return Optional.of(new Dashboard(new UserId(project.getId()), project));
    }
}

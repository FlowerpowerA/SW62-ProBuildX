package com.construtech.buildsphere.platform.operationsManagement.infrastructure.persistence.jpa.repositories;

import com.construtech.buildsphere.platform.operationsManagement.domain.model.aggregates.Task;

import com.construtech.buildsphere.platform.operationsManagement.domain.model.valueobjects.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    boolean existsByTaskNameAndProject(String taskName, Project project);
    boolean existsByTaskNameAndIdIsNot(String taskName, Long id);
    List<Task> findAllByProject(Project project);
    List<Task> findAllByTeam(Long team);
}

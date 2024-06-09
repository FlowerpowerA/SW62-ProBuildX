package com.construtech.buildsphere.platform.operationsManagement.infrastructure.persistence.jpa.repositories;

import com.construtech.buildsphere.platform.operationsManagement.domain.model.aggregates.Worker;
import com.construtech.buildsphere.platform.operationsManagement.domain.model.valueobjects.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkerRepository extends JpaRepository<Worker, Long> {

    boolean existsByFullNameAndProject(String fullName, Project project);
    boolean existsByFullNameAndIdIsNot(String fullName, Long id);
    List<Worker> findAllByProject(Project project);
}

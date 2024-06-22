package com.construtech.buildsphere.platform.project_management.infrastructure.jpa.persistence;

import com.construtech.buildsphere.platform.project_management.domain.model.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}

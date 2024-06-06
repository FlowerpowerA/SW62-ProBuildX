package com.construtech.buildsphere.platform.operationsManagement.infrastructure.persistence.jpa.repositories;

import com.construtech.buildsphere.platform.operationsManagement.domain.model.aggregates.Team;
import com.construtech.buildsphere.platform.operationsManagement.domain.model.valueobjects.ProjectId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {

    boolean existsByTeamNameAndProjectId(String teamName, ProjectId projectId);
    boolean existsByTeamNameAndIdIsNot(String teamName, Long id);
    List<Team> findAllByProjectId(ProjectId ProjectId);
}

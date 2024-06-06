package com.construtech.buildsphere.platform.operationsManagement.infrastructure.persistence.jpa.repositories;

import com.construtech.buildsphere.platform.operationsManagement.domain.model.aggregates.Team;
import com.construtech.buildsphere.platform.operationsManagement.domain.model.valueobjects.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {

    boolean existsByTeamNameAndProject(String teamName, Project project);
    boolean existsByTeamNameAndIdIsNot(String teamName, Long id);
    List<Team> findAllByProject(Project Project);
}

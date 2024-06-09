package com.construtech.buildsphere.platform.operationsManagement.application.internal.queryservices;

import com.construtech.buildsphere.platform.operationsManagement.domain.model.aggregates.Team;
import com.construtech.buildsphere.platform.operationsManagement.domain.model.queries.GetAllTeamsByProjectIdQuery;
import com.construtech.buildsphere.platform.operationsManagement.domain.model.queries.GetTeamByIdQuery;
import com.construtech.buildsphere.platform.operationsManagement.domain.services.TeamQueryService;
import com.construtech.buildsphere.platform.operationsManagement.infrastructure.persistence.jpa.repositories.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamQueryServiceImpl implements TeamQueryService {
    private final TeamRepository teamRepository;

    public TeamQueryServiceImpl(TeamRepository teamRepository){
        this.teamRepository = teamRepository;
    }

    @Override
    public Optional<Team> handle(GetTeamByIdQuery query){
        return teamRepository.findById(query.teamId());
    }

    @Override
    public List<Team> handle(GetAllTeamsByProjectIdQuery query) {
        return teamRepository.findAllByProject(query.project());
    }


}

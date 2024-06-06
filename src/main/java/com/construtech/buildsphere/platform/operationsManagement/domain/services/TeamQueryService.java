package com.construtech.buildsphere.platform.operationsManagement.domain.services;

import com.construtech.buildsphere.platform.operationsManagement.domain.model.aggregates.Team;
import com.construtech.buildsphere.platform.operationsManagement.domain.model.queries.GetAllTeamsByProjectIdQuery;
import com.construtech.buildsphere.platform.operationsManagement.domain.model.queries.GetTeamByIdQuery;

import java.util.List;
import java.util.Optional;

public interface TeamQueryService {

    Optional<Team> handle(GetTeamByIdQuery query);
    List<Team> handle(GetAllTeamsByProjectIdQuery query);

}

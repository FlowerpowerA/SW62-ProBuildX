package com.construtech.buildsphere.platform.resourceManagement.domain.services;

import com.construtech.buildsphere.platform.resourceManagement.domain.model.aggregates.Machine;
import com.construtech.buildsphere.platform.resourceManagement.domain.model.queries.GetAllMachinesByProjectIdQuery;
import com.construtech.buildsphere.platform.resourceManagement.domain.model.queries.GetMachineByIdQuery;

import java.util.List;
import java.util.Optional;

public interface MachineQueryService {
    Optional<Machine> handle(GetMachineByIdQuery query);
    List<Machine> handle(GetAllMachinesByProjectIdQuery query);
}

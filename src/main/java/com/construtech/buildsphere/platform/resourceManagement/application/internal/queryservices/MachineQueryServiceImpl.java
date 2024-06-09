package com.construtech.buildsphere.platform.resourceManagement.application.internal.queryservices;

import com.construtech.buildsphere.platform.resourceManagement.domain.model.aggregates.Machine;
import com.construtech.buildsphere.platform.resourceManagement.domain.model.queries.GetAllMachinesByProjectIdQuery;
import com.construtech.buildsphere.platform.resourceManagement.domain.model.queries.GetMachineByIdQuery;
import com.construtech.buildsphere.platform.resourceManagement.domain.services.MachineQueryService;
import com.construtech.buildsphere.platform.resourceManagement.infrastructure.persistence.jpa.repositories.MachineRepository;

import java.util.List;
import java.util.Optional;

public class MachineQueryServiceImpl implements MachineQueryService {
    private final MachineRepository machineRepository;

    public MachineQueryServiceImpl(MachineRepository machineRepository) {
        this.machineRepository = machineRepository;
    }

    @Override
    public Optional<Machine> handle(GetMachineByIdQuery query) {
        return machineRepository.findByMachineId(query.machineId());
    }

    @Override
    public List<Machine> handle(GetAllMachinesByProjectIdQuery query) {
        return machineRepository.findAllByProjectId(query.projectId());
    }
}

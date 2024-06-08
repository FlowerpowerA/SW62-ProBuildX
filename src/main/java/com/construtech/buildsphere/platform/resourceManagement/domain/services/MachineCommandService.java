package com.construtech.buildsphere.platform.resourceManagement.domain.services;

import com.construtech.buildsphere.platform.resourceManagement.domain.model.aggregates.Machine;
import com.construtech.buildsphere.platform.resourceManagement.domain.model.commands.CreateMachineCommand;

import java.util.Optional;

public interface MachineCommandService {
    Optional<Machine> handle(CreateMachineCommand command);
}

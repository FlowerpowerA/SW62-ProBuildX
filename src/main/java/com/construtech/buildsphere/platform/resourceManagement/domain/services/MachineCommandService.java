package com.construtech.buildsphere.platform.resourceManagement.domain.services;

import com.construtech.buildsphere.platform.resourceManagement.domain.model.aggregates.Machine;
import com.construtech.buildsphere.platform.resourceManagement.domain.model.commands.CreateMachineCommand;
import com.construtech.buildsphere.platform.resourceManagement.domain.model.commands.DeleteMachineCommand;
import com.construtech.buildsphere.platform.resourceManagement.domain.model.commands.UpdateMachineCommand;

import java.util.Optional;

public interface MachineCommandService {
    Long handle(CreateMachineCommand command);
    Optional<Machine> handle(UpdateMachineCommand command);
    void handle(DeleteMachineCommand command);
}

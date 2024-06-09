package com.construtech.buildsphere.platform.resourceManagement.application.internal.commandservices;

import com.construtech.buildsphere.platform.resourceManagement.domain.model.aggregates.Machine;
import com.construtech.buildsphere.platform.resourceManagement.domain.model.commands.CreateMachineCommand;
import com.construtech.buildsphere.platform.resourceManagement.domain.model.commands.DeleteMachineCommand;
import com.construtech.buildsphere.platform.resourceManagement.domain.model.commands.UpdateMachineCommand;
import com.construtech.buildsphere.platform.resourceManagement.domain.model.valueobjects.Project;
import com.construtech.buildsphere.platform.resourceManagement.domain.services.MachineCommandService;
import com.construtech.buildsphere.platform.resourceManagement.infrastructure.persistence.jpa.repositories.MachineRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MachineCommandServiceImpl implements MachineCommandService {
    private final MachineRepository machineRepository;

    public MachineCommandServiceImpl(MachineRepository machineRepository) {
        this.machineRepository = machineRepository;
    }


    @Override
    public Long handle(CreateMachineCommand command) {
        var projectId = new Project(command.project());
        if (machineRepository.existsByMachineNameAndProject(command.machineName(), projectId)) {
            throw new IllegalArgumentException("Machine with the same name is already exists in the project");
        }
        var machine = new Machine(command);
        try {
            machineRepository.save(machine);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving machine: " + e.getMessage());
        }
        return machine.getId();
    }

    @Override
    public Optional<Machine> handle(UpdateMachineCommand command) {
        if (machineRepository.existsByMachineNameAndIdIsNot(command.machineName(), command.id())) {
            throw new IllegalArgumentException("Machine with the same name already exists in the project");
        }

        var result = machineRepository.findById(command.id());
        if (result.isEmpty()) {
            throw new IllegalArgumentException("Machine not found");
        }

        var machineToUpdate = result.get();

        try {
            var updatedMachine = machineRepository.save(machineToUpdate.updateMachine(command.machineName()
                    , command.description(), command.endDate(), command.totalCost()));
            return Optional.of(updatedMachine);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating machine: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteMachineCommand command) {
        if(!machineRepository.existsById(command.machineId())) {
            throw new IllegalArgumentException("Machine not found");
        }
        try {
            machineRepository.deleteById(command.machineId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting machine: " + e.getMessage());
        }
    }
}

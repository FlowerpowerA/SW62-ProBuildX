package com.construtech.buildsphere.platform.resourceManagement.infrastructure.persistence.jpa.repositories;

import com.construtech.buildsphere.platform.resourceManagement.domain.model.aggregates.Machine;
import com.construtech.buildsphere.platform.resourceManagement.domain.model.valueobjects.ProjectRM;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MachineRepository extends JpaRepository<Machine, Long> {
    boolean existsByMachineNameAndProject(String machineName, ProjectRM project);
    boolean existsByMachineNameAndIdIsNot(String machineName, Long id);
    List<Machine> findAllByProject(ProjectRM project);
}

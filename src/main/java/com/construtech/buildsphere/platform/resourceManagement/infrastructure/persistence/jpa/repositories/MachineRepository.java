package com.construtech.buildsphere.platform.resourceManagement.infrastructure.persistence.jpa.repositories;

import com.construtech.buildsphere.platform.resourceManagement.domain.model.aggregates.Machine;
import com.construtech.buildsphere.platform.resourceManagement.domain.model.valueobjects.ProjectId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MachineRepository extends JpaRepository<Machine, ProjectId> {
    boolean existsByMachineIdAndProjectId(Long machineId, ProjectId projectId);
    boolean existsByMachineNameAndProjectId(String machineName, ProjectId projectId);
    boolean existsByMachineNameAndProjectIdNot(String machineName, ProjectId projectId);
    Optional<Machine> findByMachineId(Long machineId);
    Optional<Machine> findByMachineIdAndProjectId(Long machineId, ProjectId projectId);
    List<Machine> findAllByProjectId(ProjectId projectId);
}

package com.construtech.buildsphere.platform.resourceManagement.infrastructure.persistence.jpa.repositories;

import com.construtech.buildsphere.platform.resourceManagement.domain.model.aggregates.Machine;
import com.construtech.buildsphere.platform.resourceManagement.domain.model.valueobjects.ProjectId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MachineRepository extends JpaRepository<Machine, Long> {
    boolean existsByMachineIdAndProjectId(Long machineId, Long projectId);
    boolean existsByMachineNameAndProjectId(String machineName, Long projectId);
    boolean existsByMachineNameAndProjectIdNot(String machineName, Long projectId);
    Optional<Machine> findByMachineId(Long machineId);
    Optional<Machine> findByMachineIdAndProjectId(Long machineId, Long projectId);
    List<Machine> findAllByProjectId(ProjectId projectId);
}

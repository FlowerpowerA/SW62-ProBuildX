package com.construtech.buildsphere.platform.resourceManagement.infrastructure.persistence.jpa.repositories;

import com.construtech.buildsphere.platform.resourceManagement.domain.model.aggregates.Material;
import com.construtech.buildsphere.platform.resourceManagement.domain.model.valueobjects.ProjectId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {
    boolean existsByMaterialIdAndProjectId(Long materialId, ProjectId projectId);
    boolean existsByMaterialNameAndProjectId(String materialName, ProjectId projectId);
    boolean existsByMaterialNameAndProjectIdNot(String materialName, Long projectId);
    Optional<Material> findByMaterialId(Long materialId);
    Optional<Material> findByMaterialIdAndProjectId(Long materialId, ProjectId projectId);
    List<Material> findAllByProjectId(ProjectId projectId);
}

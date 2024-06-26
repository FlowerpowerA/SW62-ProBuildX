package com.construtech.buildsphere.platform.documents.infrastructure.persistence.jpa.repositories;

import com.construtech.buildsphere.platform.documents.domain.model.aggregates.Document;
import com.construtech.buildsphere.platform.documents.domain.model.valueobjects.ProjectD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DocumentRepository extends JpaRepository<Document, Long>{
    boolean existsByNameAndProject(String name, ProjectD project);
    boolean existsByNameAndIdIsNot(String name, Long id);
    List<Document> findAllByProject(ProjectD project);
}

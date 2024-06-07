package com.construtech.buildsphere.platform.documents.infraestructure.persistence.jpa.repositories;

import com.construtech.buildsphere.platform.documents.domain.model.aggregates.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface DocumentRepository extends JpaRepository<Document, Long>{
    Optional<Document> findByDocumentId(String documentId);
    Optional<Document> findByDocumentIdAndProjectId(String documentId, Long projectId);
}

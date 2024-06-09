package com.construtech.buildsphere.platform.documents.application.internal.queryservices;

import com.construtech.buildsphere.platform.documents.domain.model.aggregates.Document;
import com.construtech.buildsphere.platform.documents.domain.model.queries.GetAllDocumentsByProjectIdQuery;
import com.construtech.buildsphere.platform.documents.domain.model.queries.GetDocumentByIdQuery;
import com.construtech.buildsphere.platform.documents.domain.services.DocumentQueryService;
import com.construtech.buildsphere.platform.documents.infrastructure.persistence.jpa.repositories.DocumentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentQueryServiceImpl implements DocumentQueryService {
    private final DocumentRepository documentRepository;

    public DocumentQueryServiceImpl(DocumentRepository documentRepository){
        this.documentRepository = documentRepository;
    }

    @Override
    public Optional<Document> handle(GetDocumentByIdQuery query){
        return  documentRepository.findById(query.documentId());
    }

    @Override
    public List<Document> handle(GetAllDocumentsByProjectIdQuery query){
        return documentRepository.findAllByProject(query.project());
    }

}
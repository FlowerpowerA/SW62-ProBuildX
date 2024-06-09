package com.construtech.buildsphere.platform.documents.domain.services;

import com.construtech.buildsphere.platform.documents.domain.model.aggregates.Document;
import com.construtech.buildsphere.platform.documents.domain.model.queries.GetAllDocumentsByProjectIdQuery;
import com.construtech.buildsphere.platform.documents.domain.model.queries.GetDocumentByIdQuery;

import java.util.List;
import java.util.Optional;

public interface DocumentQueryService {

    Optional<Document> handle(GetDocumentByIdQuery query);
    List<Document> handle(GetAllDocumentsByProjectIdQuery query);
}

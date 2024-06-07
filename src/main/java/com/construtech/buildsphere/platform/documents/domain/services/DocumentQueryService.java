package com.construtech.buildsphere.platform.documents.domain.services;

import com.construtech.buildsphere.platform.documents.domain.model.aggregates.Document;
import com.construtech.buildsphere.platform.documents.domain.model.queries.GetAllDocumentsByProjectIdQuery;

import java.util.List;

public interface DocumentQueryService {
    List<Document> handle(GetAllDocumentsByProjectIdQuery query);
}

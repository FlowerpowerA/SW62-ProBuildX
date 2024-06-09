package com.construtech.buildsphere.platform.documents.domain.services;

import com.construtech.buildsphere.platform.documents.domain.model.aggregates.Document;
import com.construtech.buildsphere.platform.documents.domain.model.commands.CreateDocumentCommand;
import com.construtech.buildsphere.platform.documents.domain.model.commands.DeleteDocumentCommand;
import com.construtech.buildsphere.platform.documents.domain.model.commands.UpdateDocumentCommand;

import java.util.Optional;

public interface DocumentCommandService {
    Long handle(CreateDocumentCommand command);
    Optional<Document> handle(UpdateDocumentCommand command);
    void handle(DeleteDocumentCommand command);
}

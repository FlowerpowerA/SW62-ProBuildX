package com.construtech.buildsphere.platform.documents.domain.services;

import com.construtech.buildsphere.platform.documents.domain.model.commands.addDocumentCommand;
import com.construtech.buildsphere.platform.documents.domain.model.commands.deleteDocumentCommand;
import com.construtech.buildsphere.platform.documents.domain.model.commands.updateDocumentCommand;

import java.util.Optional;

public interface DocumentCommandService {
    Long handle(addDocumentCommand command);
    Optional<Long> handle(updateDocumentCommand command);
    void handle(deleteDocumentCommand command);
}

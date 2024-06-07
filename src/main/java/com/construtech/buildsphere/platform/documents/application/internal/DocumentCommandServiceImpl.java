package com.construtech.buildsphere.platform.documents.application.internal;

import com.construtech.buildsphere.platform.documents.domain.exceptions.DocumentNotFoundException;
import com.construtech.buildsphere.platform.documents.domain.model.commands.addDocumentCommand;
import com.construtech.buildsphere.platform.documents.domain.model.commands.deleteDocumentCommand;
import com.construtech.buildsphere.platform.documents.domain.model.commands.updateDocumentCommand;
import com.construtech.buildsphere.platform.documents.domain.services.DocumentCommandService;
import com.construtech.buildsphere.platform.documents.infraestructure.persistence.jpa.repositories.DocumentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DocumentCommandServiceImpl implements DocumentCommandService {

    private final DocumentRepository DocumentRepository;

    public DocumentCommandServiceImpl(DocumentRepository documentRepository) {
        DocumentRepository = documentRepository;
    }

    @Override
    public Long handle(addDocumentCommand command) {
        return null;
    }

    @Override
    public Optional<Long> handle(updateDocumentCommand command) {
        if (!DocumentRepository.existsById(command.DocumentId())) {
            throw new DocumentNotFoundException(command.DocumentId());
        }
        return Optional.empty();
    }

    @Override
    public void handle(deleteDocumentCommand command) {
        if (!DocumentRepository.existsById(command.DocumentId())) {
            throw new DocumentNotFoundException(command.DocumentId());
        }
    }
}

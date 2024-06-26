package com.construtech.buildsphere.platform.documents.application.internal.commandservices;

import com.construtech.buildsphere.platform.documents.domain.model.aggregates.Document;
import com.construtech.buildsphere.platform.documents.domain.model.commands.CreateDocumentCommand;
import com.construtech.buildsphere.platform.documents.domain.model.commands.DeleteDocumentCommand;
import com.construtech.buildsphere.platform.documents.domain.model.commands.UpdateDocumentCommand;
import com.construtech.buildsphere.platform.documents.domain.model.valueobjects.ProjectD;
import com.construtech.buildsphere.platform.documents.domain.services.DocumentCommandService;
import com.construtech.buildsphere.platform.documents.infrastructure.persistence.jpa.repositories.DocumentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DocumentCommandServiceImpl implements DocumentCommandService {
    private final DocumentRepository documentRepository;

    public DocumentCommandServiceImpl(DocumentRepository documentRepository){
        this.documentRepository = documentRepository;
    }

    @Override
    public Long handle(CreateDocumentCommand command){
        var projectId = new ProjectD(command.project());
        if (documentRepository.existsByNameAndProject(command.name(), projectId)){
            throw new IllegalArgumentException("Document with the same name already exists in the projectRM");
        }
        var document = new Document(command);
        try{
            documentRepository.save(document);
        }catch (Exception e) {
            throw new IllegalArgumentException("Error while saving document: " + e.getMessage());
        }
        return document.getId();
    }

    @Override
    public Optional<Document> handle(UpdateDocumentCommand command){
        if(documentRepository.existsByNameAndIdIsNot(command.name(), command.id())){
            throw new IllegalArgumentException("Document with same name already exists");
        }
        var result = documentRepository.findById(command.id());
        if (result.isEmpty()){
            throw new IllegalArgumentException("Document does not exists");
        }
        var documentToUpdate = result.get();
        try{
            var updatedDocument = documentRepository.save(documentToUpdate.updateInformation(command.name(), command.description(), command.fileType()));
            return Optional.of(updatedDocument);
        }catch (Exception e){
            throw new IllegalArgumentException("Error while updating document: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteDocumentCommand command){
        if(!documentRepository.existsById(command.documentId())){
            throw new IllegalArgumentException("Document does not exist");
        }
        try {
            documentRepository.deleteById(command.documentId());
        }catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting document: " + e.getMessage());
        }
    }
}
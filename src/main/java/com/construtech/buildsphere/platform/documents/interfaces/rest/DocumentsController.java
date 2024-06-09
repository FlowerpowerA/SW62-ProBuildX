package com.construtech.buildsphere.platform.documents.interfaces.rest;

import com.construtech.buildsphere.platform.documents.domain.model.commands.DeleteDocumentCommand;
import com.construtech.buildsphere.platform.documents.domain.model.queries.GetAllDocumentsByProjectIdQuery;
import com.construtech.buildsphere.platform.documents.domain.model.queries.GetDocumentByIdQuery;
import com.construtech.buildsphere.platform.documents.domain.model.valueobjects.Project;
import com.construtech.buildsphere.platform.documents.domain.services.DocumentCommandService;
import com.construtech.buildsphere.platform.documents.domain.services.DocumentQueryService;
import com.construtech.buildsphere.platform.documents.interfaces.rest.resources.CreateDocumentResource;
import com.construtech.buildsphere.platform.documents.interfaces.rest.resources.DocumentResource;
import com.construtech.buildsphere.platform.documents.interfaces.rest.resources.UpdateDocumentResource;
import com.construtech.buildsphere.platform.documents.interfaces.rest.transform.CreateDocumentCommandFromResourceAssembler;
import com.construtech.buildsphere.platform.documents.interfaces.rest.transform.DocumentResourceFromEntityAssembler;
import com.construtech.buildsphere.platform.documents.interfaces.rest.transform.UpdateDocumentCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/documents", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Documents", description = "Documents Management Endpoints")
public class DocumentsController {

    private final DocumentCommandService documentCommandService;
    private final DocumentQueryService documentQueryService;

    public DocumentsController(DocumentCommandService documentCommandService, DocumentQueryService documentQueryService){
        this.documentCommandService = documentCommandService;
        this.documentQueryService = documentQueryService;
    }

    @PostMapping
    public ResponseEntity<DocumentResource> createDocument(@RequestBody CreateDocumentResource createDocumentResource){
        var createDocumentCommand = CreateDocumentCommandFromResourceAssembler.toCommandFromResource(createDocumentResource);
        var documentId = documentCommandService.handle(createDocumentCommand);
        if(documentId == 0L){
            return ResponseEntity.badRequest().build();
        }
        var getDocumentByIdQuery = new GetDocumentByIdQuery(documentId);
        var document = documentQueryService.handle(getDocumentByIdQuery);
        if(document.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        var documentResource = DocumentResourceFromEntityAssembler.toResourceFromEntity(document.get());
        return new ResponseEntity<>(documentResource, HttpStatus.CREATED);
    }

    @GetMapping("/{documentId}")
    public ResponseEntity<DocumentResource> getDocument(@PathVariable Long documentId){
        var getDocumentByIdQuery = new GetDocumentByIdQuery(documentId);
        var document = documentQueryService.handle(getDocumentByIdQuery);
        if(document.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var documentResource = DocumentResourceFromEntityAssembler.toResourceFromEntity(document.get());
        return ResponseEntity.ok(documentResource);
    }

    @GetMapping("/projectId/{projectId}")
    public  ResponseEntity<List<DocumentResource>> getAllDocumentsByProjectId(@PathVariable Long projectId){
        var project = new Project(Math.toIntExact(projectId));
        var getAllDocumentsByProjectIdQuery = new GetAllDocumentsByProjectIdQuery(project);
        var documents = documentQueryService.handle(getAllDocumentsByProjectIdQuery);
        var documentResources = documents.stream().map(DocumentResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(documentResources);
    }

    @PutMapping("/{documentId}")
    public ResponseEntity<DocumentResource> updateDocument(@PathVariable Long documentId, @RequestBody UpdateDocumentResource updateDocumentResource){
        var updateDocumentCommand = UpdateDocumentCommandFromResourceAssembler.toCommandFromResource(documentId, updateDocumentResource);
        var updateDocument = documentCommandService.handle(updateDocumentCommand);
        if(updateDocument.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        var documentResource = DocumentResourceFromEntityAssembler.toResourceFromEntity(updateDocument.get());
        return ResponseEntity.ok(documentResource);
    }

    @DeleteMapping("/{documentId}")
    public ResponseEntity<?> deleteDocument(@PathVariable Long documentId){
        var deleteDocumentCommand = new DeleteDocumentCommand(documentId);
        documentCommandService.handle(deleteDocumentCommand);
        return ResponseEntity.ok("Document deleted successfully");
    }
}
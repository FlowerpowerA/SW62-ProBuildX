package com.construtech.buildsphere.platform.documents.interfaces.rest.transform;

import com.construtech.buildsphere.platform.documents.domain.model.commands.UpdateDocumentCommand;
import com.construtech.buildsphere.platform.documents.interfaces.rest.resources.UpdateDocumentResource;

public class UpdateDocumentCommandFromResourceAssembler {
    public static UpdateDocumentCommand toCommandFromResource(Long documentId, UpdateDocumentResource resource){
        return new UpdateDocumentCommand(documentId, resource.name(), resource.description(), resource.fileType());
    }
}

package com.construtech.buildsphere.platform.documents.interfaces.rest.transform;

import com.construtech.buildsphere.platform.documents.domain.model.commands.CreateDocumentCommand;
import com.construtech.buildsphere.platform.documents.interfaces.rest.resources.CreateDocumentResource;

public class CreateDocumentCommandFromResourceAssembler {
    public static CreateDocumentCommand toCommandFromResource(CreateDocumentResource resource){
        return new CreateDocumentCommand(resource.name(), resource.description(), resource.fileType(), resource.projectId());
    }
}

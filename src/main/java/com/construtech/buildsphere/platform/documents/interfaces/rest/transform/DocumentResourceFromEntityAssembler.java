package com.construtech.buildsphere.platform.documents.interfaces.rest.transform;

import com.construtech.buildsphere.platform.documents.domain.model.aggregates.Document;
import com.construtech.buildsphere.platform.documents.interfaces.rest.resources.DocumentResource;

public class DocumentResourceFromEntityAssembler {
    public static DocumentResource toResourceFromEntity(Document entity){
        return new DocumentResource(entity.getId(), entity.getName(), entity.getDescription(), entity.getFileType(), entity.getProjectId());
    }
}

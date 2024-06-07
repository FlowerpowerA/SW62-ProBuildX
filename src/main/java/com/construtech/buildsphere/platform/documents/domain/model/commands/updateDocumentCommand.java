package com.construtech.buildsphere.platform.documents.domain.model.commands;

public record updateDocumentCommand(Long DocumentId,String Name, String Description) {
}

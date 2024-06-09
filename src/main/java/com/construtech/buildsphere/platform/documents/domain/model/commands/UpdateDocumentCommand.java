package com.construtech.buildsphere.platform.documents.domain.model.commands;

public record UpdateDocumentCommand(Long id, String name, String description, String fileType) {
}

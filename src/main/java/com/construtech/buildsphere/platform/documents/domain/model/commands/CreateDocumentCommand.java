package com.construtech.buildsphere.platform.documents.domain.model.commands;

public record CreateDocumentCommand(String name, String description, String fileType, Long project) {
}
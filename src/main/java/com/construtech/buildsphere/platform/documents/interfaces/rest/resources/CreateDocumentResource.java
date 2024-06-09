package com.construtech.buildsphere.platform.documents.interfaces.rest.resources;

public record CreateDocumentResource(String name, String description, String fileType, String url, int projectId){
}

package com.construtech.buildsphere.platform.documents.interfaces.rest.resources;

public record AddDocumentResource(String name, String description, String fileType, String url, Long projectId){
}

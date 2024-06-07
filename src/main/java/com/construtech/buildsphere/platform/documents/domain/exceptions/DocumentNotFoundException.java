package com.construtech.buildsphere.platform.documents.domain.exceptions;

public class DocumentNotFoundException extends RuntimeException{
    public DocumentNotFoundException(Long along) {
        super("Document with id " + along + " not found");
    }
}

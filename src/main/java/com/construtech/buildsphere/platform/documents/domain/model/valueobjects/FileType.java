package com.construtech.buildsphere.platform.documents.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record FileType(FileTypeEnum fileType) {
    public FileType {
        if (fileType == null) {
            throw new IllegalArgumentException("FileType cannot be null");
        }
    }

}

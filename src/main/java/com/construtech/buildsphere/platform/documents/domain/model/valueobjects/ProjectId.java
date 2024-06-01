package com.construtech.buildsphere.platform.documents.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record ProjectId (Long projectId){
    public ProjectId {
        if (projectId < 0) {
            throw new IllegalArgumentException("ProjectId cannot be null");
        }
    }

    public ProjectId() {
        this(0L);
    }
}
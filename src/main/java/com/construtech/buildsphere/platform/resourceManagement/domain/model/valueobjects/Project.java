package com.construtech.buildsphere.platform.resourceManagement.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Project(Long projectId) {
    public Project {
        if (projectId == null || projectId <= 0) {
            throw new IllegalArgumentException("ProjectId cannot be null ");
        }
    }

    public Project() {
        this(0L);
    }
}

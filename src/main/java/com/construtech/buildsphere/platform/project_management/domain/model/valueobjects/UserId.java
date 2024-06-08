package com.construtech.buildsphere.platform.project_management.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
@Embeddable
public record UserId(Long userId) {
    public UserId {
        if (userId < 0) {
            throw new IllegalArgumentException("User ID cannot be negative");
        }
    }

    public UserId() {
        this(0L);
    }
}
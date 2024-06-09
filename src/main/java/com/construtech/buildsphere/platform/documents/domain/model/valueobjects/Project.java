package com.construtech.buildsphere.platform.documents.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Project(int projectEnt) {
}
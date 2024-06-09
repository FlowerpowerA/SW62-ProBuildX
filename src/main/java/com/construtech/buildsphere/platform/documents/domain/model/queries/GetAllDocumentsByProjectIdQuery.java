package com.construtech.buildsphere.platform.documents.domain.model.queries;

import com.construtech.buildsphere.platform.documents.domain.model.valueobjects.Project;

public record GetAllDocumentsByProjectIdQuery(Project project) {
}

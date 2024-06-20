package com.construtech.buildsphere.platform.project_management.domain.model.aggregates;

import com.construtech.buildsphere.platform.project_management.domain.model.entities.Project;
import com.construtech.buildsphere.platform.project_management.domain.model.valueobjects.UserId;
import com.construtech.buildsphere.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Dashboard extends AuditableAbstractAggregateRoot<Dashboard> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private UserId userId;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    public Dashboard(UserId userId, Project project) {
        this.userId = userId;
        this.project = project;
    }

    protected Dashboard() {
        // for JPA
    }

    public void updateUser(UserId newUserId) {
        this.userId = newUserId;
    }

    public void updateProject(Project newProject) {
        this.project = newProject;
    }
}
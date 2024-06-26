package com.construtech.buildsphere.platform.resourceManagement.domain.model.aggregates;

import com.construtech.buildsphere.platform.resourceManagement.domain.model.commands.CreateMaterialCommand;
import com.construtech.buildsphere.platform.resourceManagement.domain.model.valueobjects.ProjectRM;
import com.construtech.buildsphere.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
public class Material extends AuditableAbstractAggregateRoot<Material> {
    @Embedded
    private ProjectRM project;

    @Column(nullable = false)
    @Getter
    private String materialName;

    @Column(nullable = false)
    @Getter
    private String description;

    @Column(nullable = false, updatable = false)
    @Getter
    private String receptionDate;

    @Column(nullable = false)
    @Getter
    private int amount;

    @Column(nullable = false)
    @Getter
    private double totalCost;

    @Getter
    @Column
    private String materialStatus;

    public Material() {
        this.project = new ProjectRM(null);
        this.materialName = "";
        this.description = "";
        this.materialStatus = "";
        this.receptionDate = "";
        this.amount = 0;
        this.totalCost = 0.0;
    }

    public Material(Long project, String materialName, String description, String receptionDate, int amount, double totalCost, String materialStatus) {
        this();
        this.project = new ProjectRM(project);
        this.materialName = materialName;
        this.description = description;
        this.receptionDate = receptionDate;
        this.amount = amount;
        this.totalCost = totalCost;
        this.materialStatus = materialStatus;
    }

    public Material(CreateMaterialCommand command) {
        this.materialName = command.materialName();
        this.description = command.description();
        this.receptionDate = command.receptionDate();
        this.amount = command.amount();
        this.totalCost = command.totalCost();
        this.materialStatus = command.materialStatus();
        this.project = new ProjectRM(command.project());
    }

    public Material updateMaterial(String materialName, String description, int amount, double totalCost, String status) {
        this.materialName = materialName;
        this.description = description;
        this.amount = amount;
        this.totalCost = totalCost;
        this.materialStatus = status;
        return this;
    }

    public Long getProjectId() {
        return project.projectId();
    }
}

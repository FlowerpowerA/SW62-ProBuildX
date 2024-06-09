package com.construtech.buildsphere.platform.resourceManagement.domain.model.aggregates;

import com.construtech.buildsphere.platform.resourceManagement.domain.model.commands.CreateMaterialCommand;
import com.construtech.buildsphere.platform.resourceManagement.domain.model.valueobjects.MaterialStatus;
import com.construtech.buildsphere.platform.resourceManagement.domain.model.valueobjects.Project;
import com.construtech.buildsphere.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

import java.time.LocalDate;

@Entity
public class Material extends AuditableAbstractAggregateRoot<Material> {
    @Embedded
    private Project project;

    @Column(nullable = false)
    @Getter
    private String materialName;

    @Column(nullable = false)
    @Getter
    private String description;

    @Column(nullable = false, updatable = false)
    @Getter
    private LocalDate receptionDate;

    @Column(nullable = false)
    @Getter
    private int amount;

    @Column(nullable = false)
    @Getter
    private double totalCost;

    @Getter
    @Column
    private String status;

    public Material() {
        this.project = new Project(0);
        this.materialName = "";
        this.description = "";
        this.status = "";
        this.receptionDate = LocalDate.now();
        this.amount = 0;
        this.totalCost = 0.0;
    }

    public Material(int project, String materialName, String description, String receptionDate, int amount, double totalCost, String status) {
        this();
        this.project = new Project(project);
        this.materialName = materialName;
        this.description = description;
        this.receptionDate = LocalDate.parse(receptionDate);
        this.amount = amount;
        this.totalCost = totalCost;
        this.status = status;
    }

    public Material(CreateMaterialCommand command) {
        this.materialName = command.materialName();
        this.description = command.description();
        this.receptionDate = LocalDate.parse(command.receptionDate());
        this.amount = command.amount();
        this.totalCost = command.totalCost();
        this.status = command.status();
        this.project = new Project(command.project());
    }

    public Material updateMaterial(String materialName, String description, int amount, double totalCost, String status) {
        this.materialName = materialName;
        this.description = description;
        this.amount = amount;
        this.totalCost = totalCost;
        this.status = status;
        return this;
    }

    public int getProjectId() {
        return project.projectId();
    }
}

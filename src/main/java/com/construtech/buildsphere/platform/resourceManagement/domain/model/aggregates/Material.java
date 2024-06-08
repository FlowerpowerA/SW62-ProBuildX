package com.construtech.buildsphere.platform.resourceManagement.domain.model.aggregates;

import com.construtech.buildsphere.platform.resourceManagement.domain.model.commands.CreateMaterialCommand;
import com.construtech.buildsphere.platform.resourceManagement.domain.model.valueobjects.MaterialStatus;
import com.construtech.buildsphere.platform.resourceManagement.domain.model.valueobjects.ProjectId;
import com.construtech.buildsphere.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

import java.time.LocalDate;

@Entity
public class Material extends AuditableAbstractAggregateRoot<Material> {
    @Embedded
    private ProjectId projectId;

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @Embedded
    @Getter
    private MaterialStatus status;

    public Material() {
        this.projectId = new ProjectId();
        this.materialName = Strings.EMPTY;
        this.description = Strings.EMPTY;
        this.status = MaterialStatus.EMPTY;
        this.receptionDate = LocalDate.now();
        this.amount = 0;
        this.totalCost = 0.0;
    }

    public Material(ProjectId projectId, String materialName, String description, LocalDate receptionDate, int amount, double totalCost, MaterialStatus status) {
        this();
        this.projectId = projectId;
        this.materialName = materialName;
        this.description = description;
        this.receptionDate = receptionDate;
        this.amount = amount;
        this.totalCost = totalCost;
        this.status = status;
    }

    public Material(CreateMaterialCommand command) {
        this.materialName = command.materialName();
        this.description = command.materialDescription();
        this.receptionDate = LocalDate.parse(command.receptionDate());
        this.amount = command.amount();
        this.totalCost = command.totalCost();
        this.status = MaterialStatus.valueOf(command.status());
        this.projectId = new ProjectId(command.projectId());
    }

    public Material updateMaterial(ProjectId projectId, String materialName, String description, int amount, double totalCost, MaterialStatus status) {
        this.projectId = projectId;
        this.materialName = materialName;
        this.description = description;
        this.amount = amount;
        this.totalCost = totalCost;
        this.status = status;
        return this;
    }

    public Long getProjectId() {
        return projectId.projectId();
    }
}

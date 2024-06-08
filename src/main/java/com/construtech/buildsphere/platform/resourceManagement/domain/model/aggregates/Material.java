package com.construtech.buildsphere.platform.resourceManagement.domain.model.aggregates;

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
    private String name;

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
        this.name = Strings.EMPTY;
        this.description = Strings.EMPTY;
        this.status = MaterialStatus.EMPTY;
    }

    public Material(ProjectId projectId, String name, String description, LocalDate receptionDate, int amount, double totalCost, MaterialStatus status) {
        this();
        this.projectId = projectId;
        this.name = name;
        this.description = description;
        this.receptionDate = receptionDate;
        this.amount = amount;
        this.totalCost = totalCost;
        this.status = status;
    }

    public Material updateMaterial(ProjectId projectId, String name, String description, int amount, double totalCost, MaterialStatus status) {
        this.projectId = projectId;
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.totalCost = totalCost;
        this.status = status;
        return this;
    }

}

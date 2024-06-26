package com.construtech.buildsphere.platform.resourceManagement.domain.model.aggregates;

import com.construtech.buildsphere.platform.resourceManagement.domain.model.commands.CreateMachineCommand;
import com.construtech.buildsphere.platform.resourceManagement.domain.model.valueobjects.ProjectRM;
import com.construtech.buildsphere.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
public class Machine extends AuditableAbstractAggregateRoot<Machine> {
    @Embedded
    private ProjectRM project;

    @Column(nullable = false)
    @Getter
    private String machineName;

    @Column(nullable = false)
    @Getter
    private String description;

    @Column(nullable = false, updatable = false)
    @Getter
    private String receptionDate;

    @Column(nullable = false)
    @Getter
    private String endDate;

    @Column(nullable = false)
    @Getter
    private double totalCost;

    public Machine() {
        this.project = new ProjectRM(null);
        this.machineName = "";
        this.description = "";
        this.receptionDate = "";
        this.endDate = "";
        this.totalCost = 0.0;
    }

    public Machine(Long project, String machineName, String description, String receptionDate, String endDate, double totalCost) {
        this();
        this.project = new ProjectRM(project);
        this.machineName = machineName;
        this.description = description;
        this.receptionDate = receptionDate;
        this.endDate = endDate;
        this.totalCost = totalCost;
    }

    public Machine(CreateMachineCommand command) {
        this.machineName = command.machineName();
        this.description = command.description();
        this.receptionDate = command.receptionDate();
        this.endDate = command.endDate();
        this.totalCost = command.totalCost();
        this.project = new ProjectRM(command.project());
    }

    public Machine updateMachine(String machineName, String description, String endDate, double totalCost) {
        this.machineName = machineName;
        this.description = description;
        this.endDate = endDate;
        this.totalCost = totalCost;
        return this;
    }

    public Long getProjectRM() {
        return this.project.projectId();
    }
}

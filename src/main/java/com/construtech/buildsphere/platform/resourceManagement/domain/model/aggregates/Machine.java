package com.construtech.buildsphere.platform.resourceManagement.domain.model.aggregates;

import com.construtech.buildsphere.platform.resourceManagement.domain.model.commands.CreateMachineCommand;
import com.construtech.buildsphere.platform.resourceManagement.domain.model.valueobjects.Project;
import com.construtech.buildsphere.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Entity
public class Machine extends AuditableAbstractAggregateRoot<Machine> {
    @Embedded
    private Project project;

    @Column(nullable = false)
    @Getter
    private String machineName;

    @Column(nullable = false)
    @Getter
    private String description;

    @Column(nullable = false, updatable = false)
    @Getter
    private LocalDate receptionDate;

    @Column(nullable = false)
    @Getter
    private LocalDate endDate;

    @Column(nullable = false)
    @Getter
    private double totalCost;

    public Machine() {
        this.project = new Project(0);
        this.machineName = "";
        this.description = "";
        this.receptionDate = LocalDate.now();
        this.endDate = null;
        this.totalCost = 0.0;
    }

    public Machine(int project, String machineName, String description, String receptionDate, String endDate, double totalCost) {
        this();
        this.project = new Project(project);
        this.machineName = machineName;
        this.description = description;
        this.receptionDate = LocalDate.parse(receptionDate);
        this.endDate = LocalDate.parse(endDate);
        this.totalCost = totalCost;
    }

    public Machine(CreateMachineCommand command) {
        this.machineName = command.machineName();
        this.description = command.description();
        this.receptionDate = LocalDate.parse(command.receptionDate());
        this.endDate = LocalDate.parse(command.endDate());
        this.totalCost = command.totalCost();
        this.project = new Project(command.project());
    }

    public Machine updateMachine(String machineName, String description, String endDate, double totalCost) {
        this.machineName = machineName;
        this.description = description;
        this.endDate = LocalDate.parse(endDate);
        this.totalCost = totalCost;
        return this;
    }

    public int getProject() {
        return this.project.projectId();
    }

}

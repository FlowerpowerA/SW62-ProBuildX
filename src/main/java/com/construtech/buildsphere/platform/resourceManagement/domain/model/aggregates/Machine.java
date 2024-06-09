package com.construtech.buildsphere.platform.resourceManagement.domain.model.aggregates;

import com.construtech.buildsphere.platform.resourceManagement.domain.model.commands.CreateMachineCommand;
import com.construtech.buildsphere.platform.resourceManagement.domain.model.valueobjects.ProjectId;
import com.construtech.buildsphere.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

import java.time.LocalDate;

@Entity
public class Machine extends AuditableAbstractAggregateRoot<Machine> {
    @Embedded
    private ProjectId projectId;

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
        this.projectId = new ProjectId();
        this.machineName = Strings.EMPTY;
        this.description = Strings.EMPTY;
        this.receptionDate = LocalDate.now();
        this.endDate = null;
        this.totalCost = 0.0;
    }

    public Machine(ProjectId projectId, String machineName, String description, LocalDate receptionDate, LocalDate endDate, double totalCost) {
        this();
        this.projectId = projectId;
        this.machineName = machineName;
        this.description = description;
        this.receptionDate = receptionDate;
        this.endDate = endDate;
        this.totalCost = totalCost;
    }

    public Machine(CreateMachineCommand command) {
        this.machineName = command.machineName();
        this.description = command.description();
        this.receptionDate = LocalDate.parse(command.receptionDate());
        this.endDate = LocalDate.parse(command.endDate());
        this.totalCost = command.totalCost();
        this.projectId = new ProjectId(command.projectId());
    }

    public Machine updateMachine(String machineName, String description, LocalDate endDate, double totalCost) {
        this.machineName = machineName;
        this.description = description;
        this.endDate = endDate;
        this.totalCost = totalCost;
        return this;
    }

    public Long getProjectId() {
        return this.projectId.projectId();
    }

}

package com.construtech.buildsphere.platform.operationsManagement.domain.model.aggregates;

import com.construtech.buildsphere.platform.operationsManagement.domain.model.valueobjects.Project;
import com.construtech.buildsphere.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Worker extends AuditableAbstractAggregateRoot<Worker> {

    @Embedded
    private Project project;

    @Column
    private String fullName;

    @Column
    private String role;

    @Column
    private int hoursWorked;

    @ManyToOne
    @JoinColumn(name = "team_id") // Nombre de la columna en la tabla
    private Team team; // Relación con la entidad Team


    public Worker() {
        this.project = new Project(0);
        this.fullName = "";
        this.role = "";
        this.hoursWorked = 0;
    }

    public Worker(int project, String fullName, String role, int hoursWorked, Team team){
        this();
        this.project = new Project(project);
        this.fullName = fullName;
        this.role = role;
        this.hoursWorked = hoursWorked;
        this.team = team;
    }

}

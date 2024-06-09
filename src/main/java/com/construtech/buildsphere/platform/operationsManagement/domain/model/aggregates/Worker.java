package com.construtech.buildsphere.platform.operationsManagement.domain.model.aggregates;

import com.construtech.buildsphere.platform.operationsManagement.domain.model.commands.CreateWorkerCommand;
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

    @Column
    private Long team;//Foreign key


    public Worker() {
        this.project = new Project(0);
        this.fullName = "";
        this.role = "";
        this.hoursWorked = 0;
    }

    public Worker(int project, String fullName, String role, int hoursWorked, Long team){
        this();
        this.project = new Project(project);
        this.fullName = fullName;
        this.role = role;
        this.hoursWorked = hoursWorked;
        this.team = team;
    }

    public Worker(CreateWorkerCommand command){
        this.fullName = command.fullName();
        this.role = command.role();
        this.hoursWorked = command.hoursWorked();
        this.project = new Project(command.project());
        this.team = command.teamId();
    }

    public Worker updateInformation(String fullName, String role, int hoursWorked, Long team){
        this.fullName = fullName;
        this.role = role;
        this.hoursWorked = hoursWorked;
        this.team = team;
        return this;
    }

    public int getProjectId(){
        return project.projectEnt();
    }

}

package com.construtech.buildsphere.platform.operationsManagement.domain.model.aggregates;

import com.construtech.buildsphere.platform.operationsManagement.domain.model.commands.CreateTaskCommand;
import com.construtech.buildsphere.platform.operationsManagement.domain.model.valueobjects.Project;
import com.construtech.buildsphere.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Entity
public class Task extends AuditableAbstractAggregateRoot<Task> {

    @Embedded
    private Project project;

    @Column
    private String taskName;

    @Column
    private String taskDescription;

    @Column
    private LocalDate startDate;

    @Column
    private LocalDate maxEndDate;

    @Column
    private Long team;//Foreign key

    public Task(){
        this.project = new Project(0);
        this.taskName = "";
        this.taskDescription = "";
        this.startDate = null;
        this.maxEndDate = null;
    }

    public Task(int project, String taskName, String taskDescription, String startDate, String maxEndDate){
        this();
        this.project = new Project(project);
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.startDate = LocalDate.parse(startDate);
        this.maxEndDate = LocalDate.parse(maxEndDate);
    }

    public Task(CreateTaskCommand command){
        this.taskName = command.taskName();
        this.taskDescription = command.taskDescription();
        this.startDate = LocalDate.parse(command.startDate());
        this.maxEndDate = LocalDate.parse(command.maxEndDate());
        this.project = new Project(command.project());
        this.team = command.teamId();
    }

    public Task updateInformation(String taskName, String taskDescription, String startDate, String maxEndDate, Long team){
        this.taskName = taskName;
        this.taskDescription =taskDescription;
        this.startDate = LocalDate.parse(startDate);
        this.maxEndDate = LocalDate.parse(maxEndDate);
        this.team = team;
        return this;
    }

    public int getProjectId(){
        return project.projectEnt();
    }

}

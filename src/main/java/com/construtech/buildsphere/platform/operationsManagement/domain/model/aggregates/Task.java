package com.construtech.buildsphere.platform.operationsManagement.domain.model.aggregates;

import com.construtech.buildsphere.platform.operationsManagement.domain.model.commands.CreateTaskCommand;
import com.construtech.buildsphere.platform.operationsManagement.domain.model.valueobjects.Project;
import com.construtech.buildsphere.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        this.project = new Project(null);
        this.taskName = "";
        this.taskDescription = "";
        this.startDate = null;
        this.maxEndDate = null;
    }

    public Task(Long project, String taskName, String taskDescription, String startDate, String maxEndDate){
        this();
        this.project = new Project(project);
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.startDate = LocalDate.parse(startDate, formatter);
        this.maxEndDate = LocalDate.parse(maxEndDate, formatter);
    }

    public Task(CreateTaskCommand command){
        this.taskName = command.taskName();
        this.taskDescription = command.taskDescription();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.startDate = LocalDate.parse(command.startDate(),formatter);
        this.maxEndDate = LocalDate.parse(command.maxEndDate(),formatter);
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

    public Long getProjectId(){
        return project.projectEnt();
    }

    public String getStartDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String fechaString = startDate.format(formatter);
        return fechaString;
    }
    public String getMaxEndDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String fechaString = maxEndDate.format(formatter);
        return fechaString;
    }
}

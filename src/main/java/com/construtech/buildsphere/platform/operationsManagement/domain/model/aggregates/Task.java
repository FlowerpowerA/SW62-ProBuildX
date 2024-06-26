package com.construtech.buildsphere.platform.operationsManagement.domain.model.aggregates;

import com.construtech.buildsphere.platform.operationsManagement.domain.model.commands.CreateTaskCommand;
import com.construtech.buildsphere.platform.operationsManagement.domain.model.valueobjects.Project;
import com.construtech.buildsphere.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;

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
    private String startDate;

    @Column
    private String maxEndDate;

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
        this.startDate = startDate;
        this.maxEndDate = maxEndDate;
    }

    public Task(CreateTaskCommand command){
        this.taskName = command.taskName();
        this.taskDescription = command.taskDescription();
        this.startDate = command.startDate();
        this.maxEndDate = command.maxEndDate();
        this.project = new Project(command.project());
        this.team = command.teamId();
    }

    public Task updateInformation(String taskName, String taskDescription, String startDate, String maxEndDate, Long team){
        this.taskName = taskName;
        this.taskDescription =taskDescription;
        this.startDate = startDate;
        this.maxEndDate = maxEndDate;
        this.team = team;
        return this;
    }

    public Long getProjectId(){
        return project.projectEnt();
    }

    public String getStartDate(){
        return startDate;
    }

    public String getMaxEndDate(){
        return maxEndDate;
    }
}

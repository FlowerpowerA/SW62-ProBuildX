package com.construtech.buildsphere.platform.operationsManagement.domain.model.aggregates;

import com.construtech.buildsphere.platform.operationsManagement.domain.model.commands.CreateTeamCommand;
import com.construtech.buildsphere.platform.operationsManagement.domain.model.valueobjects.Project;
import com.construtech.buildsphere.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;


@Getter
@Entity
public class Team extends AuditableAbstractAggregateRoot<Team> {

    @Column
    private String teamName;

    @Column
    private String description;

    @Embedded
    private Project project;

    public Team(){
        this.teamName = Strings.EMPTY;
        this.description = Strings.EMPTY;
        this.project = new Project(null);
    }

    public Team(String teamName, String description, Long project){
        this();
        this.teamName = teamName;
        this.description = description;
        this.project = new Project(project);
    }

    public Team(CreateTeamCommand command){
        this.teamName = command.teamName();
        this.description = command.description();
        this.project = new Project(command.project());
    }

    public Team updateInformation(String teamName, String description){
        this.teamName = teamName;
        this.description = description;
        return this;
    }

    public Long getProjectId(){
        return project.projectEnt();
    }
}

package com.construtech.buildsphere.platform.operationsManagement.domain.model.aggregates;

import com.construtech.buildsphere.platform.operationsManagement.domain.model.commands.CreateTeamCommand;
import com.construtech.buildsphere.platform.operationsManagement.domain.model.valueobjects.ProjectId;
import com.construtech.buildsphere.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

@Getter
@Entity
public class Team extends AuditableAbstractAggregateRoot<Team> {

    @Embedded
    private String teamName;
    @Embedded
    private String description;


    @Embedded
    @JoinColumn(name = "project_id")
    private ProjectId projectId;

    public Team(){
        this.teamName = Strings.EMPTY;
        this.description = Strings.EMPTY;
        this.projectId = new ProjectId(0);
    }

    public Team(String teamName, String description, int projectId){
        this();
        this.teamName = teamName;
        this.description = description;
        this.projectId = new ProjectId(projectId);
    }

    public Team(CreateTeamCommand command){
        this.teamName = command.teamName();
        this.description = command.description();
        this.projectId = new ProjectId(command.projectId());
    }
    public Team updateInformation(String teamName, String description){
        this.teamName = teamName;
        this.description = description;
        return this;
    }

    public int getProjectId(){
        return projectId.id();
    }
}

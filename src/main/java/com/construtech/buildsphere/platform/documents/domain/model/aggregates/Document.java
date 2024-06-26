package com.construtech.buildsphere.platform.documents.domain.model.aggregates;

import com.construtech.buildsphere.platform.documents.domain.model.commands.CreateDocumentCommand;
import com.construtech.buildsphere.platform.documents.domain.model.valueobjects.ProjectD;
import com.construtech.buildsphere.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Document extends AuditableAbstractAggregateRoot<Document> {

    @Embedded
    private ProjectD project;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String fileType;


    public Document() {
        this.project = new ProjectD(0L);
        this.name = "";
        this.description = "";
        this.fileType = "";
    }

    public Document(Long project, String name, String description, String fileType){
        this();
        this.project = new ProjectD(project);
        this.name = name;
        this.description = description;
        this.fileType = fileType;
    }

    public Document(CreateDocumentCommand command){
        this.name = command.name();
        this.description = command.description();
        this.fileType = command.fileType();
        this.project = new ProjectD(command.project());
    }

    public Document updateInformation(String name, String description, String fileType){
        this.name = name;
        this.description = description;
        this.fileType = fileType;
        return this;
    }

    public Long getProjectId(){
        return project.projectEnt();
    }

}

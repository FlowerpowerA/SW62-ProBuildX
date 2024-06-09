package com.construtech.buildsphere.platform.documents.domain.model.aggregates;

import com.construtech.buildsphere.platform.documents.domain.model.commands.CreateDocumentCommand;
import com.construtech.buildsphere.platform.documents.domain.model.valueobjects.Project;
import com.construtech.buildsphere.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Document extends AuditableAbstractAggregateRoot<Document> {

    @Embedded
    private Project project;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String fileType;


    public Document() {
        this.project = new Project(0);
        this.name = "";
        this.description = "";
        this.fileType = "";
    }

    public Document(int project, String name, String description, String fileType){
        this();
        this.project = new Project(project);
        this.name = name;
        this.description = description;
        this.fileType = fileType;
    }

    public Document(CreateDocumentCommand command){
        this.name = command.name();
        this.description = command.description();
        this.fileType = command.fileType();
        this.project = new Project(command.project());
    }

    public Document updateInformation(String name, String description, String fileType){
        this.name = name;
        this.description = description;
        this.fileType = fileType;
        return this;
    }

    public int getProjectId(){
        return project.projectEnt();
    }

}

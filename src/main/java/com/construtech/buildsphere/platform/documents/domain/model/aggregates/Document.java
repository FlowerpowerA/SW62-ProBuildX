package com.construtech.buildsphere.platform.documents.domain.model.aggregates;

import com.construtech.buildsphere.platform.documents.domain.model.valueobjects.FileType;
import com.construtech.buildsphere.platform.documents.domain.model.valueobjects.FileTypeEnum;
import com.construtech.buildsphere.platform.documents.domain.model.valueobjects.ProjectId;
import com.construtech.buildsphere.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import org.apache.logging.log4j.util.Strings;

@Entity
public class Document extends AuditableAbstractAggregateRoot<Document> {
    /**
    * The Project Id for this document
    */
    @Embedded
    private ProjectId projectId;

    private String name;

    private String description;

    @Embedded
    private FileType fileType;

    private String url;

    public Document() {
        this.projectId = new ProjectId();
        this.name = Strings.EMPTY;
        this.description = Strings.EMPTY;
        this.fileType = new FileType(FileTypeEnum.valueOf("OTHER"));
        this.url = Strings.EMPTY;
    }

    public Document(String Name, String Description, FileType FileType) {
        this();
        this.name = Name;
        this.description = Description;
        this.fileType = FileType;
    }
}

package com.construtech.buildsphere.platform.project_management.domain.model.entities;
import com.construtech.buildsphere.platform.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@NoArgsConstructor
@Entity
public class Project extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String location;
    private String startDate;
    private String expectedEndDate;
    private String budget;
    private String urlImage;

    public Project(String name, String description, String location, String startDate, String expectedEndDate, String budget, String urlImage) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.startDate = startDate;
        this.expectedEndDate = expectedEndDate;
        this.budget = budget;
        this.urlImage = urlImage;
    }

    public void updateDetails(String name, String description, String location, String startDate, String expectedEndDate, String budget, String urlImage) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.startDate = startDate;
        this.expectedEndDate = expectedEndDate;
        this.budget = budget;
        this.urlImage = urlImage;
    }
}


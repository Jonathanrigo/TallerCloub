package com.poli.taller1.taller1.Models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "backlog")
public class BacklogModel {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }

    public ProjectModel getProject() {
        return project;
    }

    public void setProject(ProjectModel project) {
        this.project = project;
    }

    public List<ProjectTaskModel> getProjectTask() {
        return projectTask;
    }

    public void setProjectTask(List<ProjectTaskModel> projectTask) {
        this.projectTask = projectTask;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "No null")
    private String projectIdentifier;

    //Relación con project
    @OneToOne
    @JoinColumn(name="project_id")
    private ProjectModel project;

    //Relación con ProjectTask
    @JsonManagedReference
    @OneToMany(mappedBy="backlog" , fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<ProjectTaskModel> projectTask;

}

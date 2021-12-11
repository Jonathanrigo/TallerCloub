package com.poli.taller1.taller1.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "projecttask")
public class ProjectTaskModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column
    @NotNull(message = "No null")
    private String nombre;

    @Column
    @NotNull(message = "No null")
    private String summary;

    @Column
    private String acceptanceCriteria;

    @Column
    @Pattern(regexp="^(not started|in progress|completed|deleted)$", message="status invalido")
    private String status;

    @Column
    @Min(value = 1)
    @Max(value = 5)
    private Integer priority;

    @Column
    @Min(value = 1)
    @Max(value = 8)
    private Double hours;

    @Column
    private Date startDate;

    @Column
    private Date endDate;

    @Column(updatable = false)
    private String projectIdentifier;

    //Relación con backlog
    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "backlog_id")
    private BacklogModel backlog;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectTaskModel that = (ProjectTaskModel) o;
        return Objects.equals(id, that.id) && Objects.equals(nombre, that.nombre) && Objects.equals(summary, that.summary) && Objects.equals(acceptanceCriteria, that.acceptanceCriteria) && Objects.equals(status, that.status) && Objects.equals(priority, that.priority) && Objects.equals(hours, that.hours) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate) && Objects.equals(projectIdentifier, that.projectIdentifier) && Objects.equals(backlog, that.backlog);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, summary, acceptanceCriteria, status, priority, hours, startDate, endDate, projectIdentifier, backlog);
    }
}

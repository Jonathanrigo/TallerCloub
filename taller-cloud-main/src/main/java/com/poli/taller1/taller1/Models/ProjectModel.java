
package com.poli.taller1.taller1.Models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Table(name = "project")
public class ProjectModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(unique = true)
    @NotNull(message = "No null")
    private String projectName;

    @Column(unique = true, updatable = false)
    @NotNull(message = "No null")
    @Size(min=5, max=7)
    private String projectIdentifier;

    @Column
    @NotNull(message = "No null")
    private String description;

    @Column
    private LocalDateTime startDate;

    @Column
    private LocalDateTime endDate;

    //Relación con backlog
    @OneToOne(mappedBy="project")
    private BacklogModel backlog;

}
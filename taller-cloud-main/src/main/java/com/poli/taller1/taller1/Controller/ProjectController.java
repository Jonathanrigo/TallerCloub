package com.poli.taller1.taller1.Controller;

import com.poli.taller1.taller1.Models.ProjectModel;
import com.poli.taller1.taller1.Services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.net.URI;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @GetMapping()
    public List<ProjectModel> mostrarProject(){
        return projectService.mostrarProject();
    }

    @PostMapping()
    public ResponseEntity<ProjectModel> crearProyecto(@Valid @RequestBody ProjectModel project, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(400).build();
        }

        List<ProjectModel> projects = projectService.mostrarProject();
        List<ResponseEntity> filter;
        filter = projects.stream()
                .filter(project1 -> project1.getProjectName().equals(project.getProjectName()))
                .filter(project1 -> project1.getProjectIdentifier().equals(project.getProjectIdentifier()))
                .map(project1 -> {return ResponseEntity.status(400).build();})
                .collect(Collectors.toList());

        if(filter.size() > 0){
            return filter.get(0);
        }
        project.setStartDate(LocalDateTime.now());
        ProjectModel projectCreated = projectService.crearProject(project);
        return ResponseEntity.created(URI.create("/crearProyecto")).body(projectCreated);
    }
    @PutMapping()
    public ProjectModel editarProject(@RequestBody ProjectModel project){
        return projectService.crearProject(project);
    }

    @DeleteMapping(path = "/{id}")
    public String eliminarProject(@PathVariable("id") Long id){
        boolean seElimino = projectService.eliminarProject(id);
        if(seElimino){
            return "Se elimino";
        }
        return "No se pudo eliminar el usuario con el id " + id;
    }

}

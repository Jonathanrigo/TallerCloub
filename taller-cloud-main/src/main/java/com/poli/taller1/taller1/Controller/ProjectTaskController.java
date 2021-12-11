package com.poli.taller1.taller1.Controller;

import com.poli.taller1.taller1.Models.ProjectTaskModel;
import com.poli.taller1.taller1.Services.ProjectTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.net.URI;

@RestController
@RequestMapping("/projecttask")
public class ProjectTaskController {

    @Autowired
    ProjectTaskService projectTaskService;

    @GetMapping()
    public List<ProjectTaskModel> mostrarProjectTask(){
        return projectTaskService.mostrarProjectTask();
    }

    @PostMapping()
    public ProjectTaskModel crearProjectTask(@RequestBody ProjectTaskModel projectTask){
        return projectTaskService.crearProjectTask(projectTask);
    }

    @PutMapping()
    public ProjectTaskModel editarProjectTask(@RequestBody ProjectTaskModel projectTask){
        return projectTaskService.crearProjectTask(projectTask);
    }

    @PostMapping(value = "/crearTarea")
    public ResponseEntity<ProjectTaskModel> crearProjectTask(@Valid @RequestBody ProjectTaskModel projectTask, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntity.status(400).build();
        }

        List<ProjectTaskModel> projectTasks = projectTaskService.mostrarProjectTask();
        List<ResponseEntity> filter;
        filter = projectTasks.stream()
                .filter(projectTask1 -> projectTask1.getProjectIdentifier().equals(projectTask.getProjectIdentifier()))
                .filter(projectTask1 -> projectTask1.getNombre().equals(projectTask.getNombre()))
                .map(projectTask1 -> {return ResponseEntity.status(400).build();})
                .collect(Collectors.toList());

        if(filter.size() > 0){
            return filter.get(0);
        }
        projectTask.setStartDate(new Date());
        ProjectTaskModel taskCreated =  projectTaskService.crearProjectTask(projectTask);
        return ResponseEntity.created(URI.create("/crearTarea")).body(taskCreated);
    }

    @GetMapping(value = "/proyecto/{projectIdentifier}")
    public ResponseEntity<List<ProjectTaskModel>> mostrarTareasProyectos(@PathVariable String projectIdentifier){
        List<ProjectTaskModel> projectTask = projectTaskService.mostrarProjectTask();
        projectTask.stream()
                .filter(projectTask1 -> projectTask1.getProjectIdentifier().equals(projectIdentifier));

        if(projectTask.size() == 0){
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(projectTask);
    }

    @GetMapping(value = "/horas/proyecto/{projectIdentifier}")
    public ResponseEntity<Double> mostrarTotalHorasProyecto(@PathVariable String projectIdentifier){
        List<ProjectTaskModel> projectTask = projectTaskService.mostrarProjectTask();
        List<Double> horasProyecto = new ArrayList<>();
        horasProyecto = projectTask.stream()
                .filter(projectTask1 -> projectTask1.getProjectIdentifier().equals(projectIdentifier))
                .filter(projectTask1 -> !projectTask1.getStatus().equals("deleted"))
                .map(projectTask1 -> projectTask1.getHours())
                .reduce((contador,tiempoProjectTask) ->{return contador = contador + tiempoProjectTask;})
                .stream().collect(Collectors.toList());

        if(projectTask.size() == 0){
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.ok(horasProyecto.get(0));
    }

    @GetMapping(value = "/horas/proyecto/{projectIdentifier}/{status}")
    public ResponseEntity<Double> mostrarTotalHorasProyectoStatus(@PathVariable String projectIdentifier, @PathVariable String status){
        List<ProjectTaskModel> projectTask = projectTaskService.mostrarProjectTask();
        List<Double> horasProyecto = new ArrayList<>();
        horasProyecto = projectTask.stream()
                .filter(projectTask1 -> projectTask1.getProjectIdentifier().equals(projectIdentifier))
                .filter(projectTask1 -> projectTask1.getStatus().equals(status))
                .map(projectTask1 -> projectTask1.getHours())
                .reduce((contador,tiempoProjectTask) ->{return contador = contador + tiempoProjectTask;})
                .stream().collect(Collectors.toList());

        if(projectTask.size() == 0){
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.ok(horasProyecto.get(0));
    }

    @PutMapping(path = "/{id}/{projectIdentifier}")
    public ResponseEntity<String> eliminarProjectTask(@PathVariable Long id, @PathVariable String projectIdentifier){
        ProjectTaskModel taskToDelete = projectTaskService.mostrarProjectTask()
                .stream().filter(projectTask -> projectTask.getId().equals(id))
                .filter(projectTask -> projectTask.getProjectIdentifier().equals(projectIdentifier))
                .collect(Collectors.toList()).get(0);
        taskToDelete.setStatus("deleted");
        ProjectTaskModel projectTaskUpdated = projectTaskService.editarProjectTask(taskToDelete);
        if(projectTaskUpdated.getStatus().equals("deleted")){
            return ResponseEntity.ok().body("Se elimino");
        }
        return ResponseEntity.ok().body("No se pudo eliminar la tarea con id "+ id + " y project id "+ projectIdentifier);
    }

    @DeleteMapping(path = "/{id}")
    public String eliminarProjectTask(@PathVariable("id") Long id){
        boolean seElimino = projectTaskService.eliminarProjectTask(id);
        if(seElimino){
            return "Se elimino";
        }
        return "No se pudo eliminar el usuario con el id " + id;
    }
}

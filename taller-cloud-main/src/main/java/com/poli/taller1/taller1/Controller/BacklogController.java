package com.poli.taller1.taller1.Controller;

import com.poli.taller1.taller1.Models.BacklogModel;
import com.poli.taller1.taller1.Services.BacklogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/backlog")
public class BacklogController {

    @Autowired
    BacklogService backlogService;

    @GetMapping()
    public List<BacklogModel> mostrarBacklogs(){
        return backlogService.mostrarBacklogs();
    }

    @PostMapping()
    public BacklogModel crearBacklog(@RequestBody BacklogModel backlog){

        return backlogService.crearBacklog(backlog);
    }

    @PutMapping()
    public BacklogModel editarBacklog(@RequestBody BacklogModel backlog){
        return backlogService.crearBacklog(backlog);
    }
    @PostMapping()
    @Validated
    public ResponseEntity<BacklogModel> crearBacklog(@Valid @RequestBody BacklogModel backlog, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntity.status(400).build();
        }
        BacklogModel backlogCreated = backlogService.crearBacklog(backlog);
        return ResponseEntity.created(URI.create("crearBacklog")).body(backlogCreated);
    }

    @DeleteMapping(path = "/{id}")
    public String eliminarBacklog(@PathVariable("id") Long id){
        boolean seElimino = backlogService.eliminarBacklog(id);
        if(seElimino){
            return "Se elimino";
        }
        return "No se pudo eliminar el usuario con el id " + id;
    }
}

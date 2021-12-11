package com.poli.taller1.taller1.Services;

import com.poli.taller1.taller1.Models.BacklogModel;
import com.poli.taller1.taller1.Repositories.BacklogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BacklogService {

    @Autowired
    BacklogRepository backlogRepository;

    //Crear
    public BacklogModel crearBacklog(BacklogModel backlog){
        return backlogRepository.save(backlog);
    }

    //Mostrar
    public List<BacklogModel> mostrarBacklogs(){
        return (List<BacklogModel>) backlogRepository.findAll();
    }

    //Editar
    public BacklogModel editarBacklog(BacklogModel backlog){
        return backlogRepository.save(backlog);
    }

    //Eliminar
    public boolean eliminarBacklog(Long id){
        try {
            backlogRepository.deleteById(id);
            return true;
        }catch (Exception error){
            return false;
        }
    }

}
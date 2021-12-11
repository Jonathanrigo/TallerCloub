package com.poli.taller1.taller1.Services;


import com.poli.taller1.taller1.Models.ProjectModel;
import com.poli.taller1.taller1.Repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    //Crear
    public ProjectModel crearProject(ProjectModel projectTask){
        return projectRepository.save(projectTask);
    }

    //Mostrar
    public List<ProjectModel> mostrarProject(){
        return (List<ProjectModel>) projectRepository.findAll();
    }

    //Editar
    public ProjectModel editarProject(ProjectModel projectTask){
        return projectRepository.save(projectTask);
    }

    //Eliminar
    public boolean eliminarProject(Long id){
        try {
            projectRepository.deleteById(id);
            return true;
        }catch (Exception error){
            return false;
        }
    }
}

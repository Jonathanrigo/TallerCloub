package com.poli.taller1.taller1.Repositories;

import com.poli.taller1.taller1.Models.ProjectModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<ProjectModel,Long> {
}

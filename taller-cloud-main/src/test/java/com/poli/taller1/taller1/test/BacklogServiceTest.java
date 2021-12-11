package com.poli.taller1.taller1.test;

import com.poli.taller1.taller1.Models.BacklogModel;
import com.poli.taller1.taller1.Repositories.BacklogRepository;
import com.poli.taller1.taller1.Services.BacklogService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BacklogServiceTest {

    @Autowired
    private BacklogRepository backlogRepository;
    @Test
    void createdBacklog(){
        BacklogModel backlogModel = BacklogModel.builder()
                .projectIdentifier("")
                .project(null)
                .projectTask(null)
                .build();
        backlogRepository.save(backlogModel);
        List<BacklogModel> backlogModelList = backlogRepository.findAll();
        Assertions.assertEquals("null");
    }
}
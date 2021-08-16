package com.vs.crudapp.controller;

import com.vs.crudapp.entity.Task;
import com.vs.crudapp.repository.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Slf4j
public class TaskController {

    @Autowired
    TaskRepository repository;

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTask(@PathVariable("id") Long id){
        Optional<Task> task = repository.findById(id);
        if (!task.isPresent()){
            log.info("TASK NOT FOUND");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();}
        else{
            log.info("TASK {} ",task.get());
            return new ResponseEntity<>(task.get(),HttpStatus.OK);
        }

    }

    @PostMapping("/tasks")
    public void saveTask(@RequestBody Task task){
        log.info("SAVE TASK: {}",task);
        repository.save(task);
    }

    @DeleteMapping("/tasks/{id}")
    public void deleteTask(@PathVariable("id") Long id){
        log.info("DELETE TASK: {}",id);
        repository.deleteById(id);
    }

    @GetMapping("/")
    public String hello(){
        return "<h1>Hello from Microservice</h1>";
    }
}
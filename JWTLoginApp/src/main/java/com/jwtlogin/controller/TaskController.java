package com.jwtlogin.controller;

import com.jwtlogin.entity.Task;
import com.jwtlogin.service.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "http://localhost:4200")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/addtask")
    public Task addTask(@RequestBody Task task) {
        return taskService.addTask(task);
    }

    @GetMapping("/getalltasks")
    public List<Task> getTasks() {
        return taskService.getAllTasks();
    }
}

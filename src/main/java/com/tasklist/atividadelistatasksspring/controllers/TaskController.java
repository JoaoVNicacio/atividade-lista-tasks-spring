package com.tasklist.atividadelistatasksspring.controllers;

import com.tasklist.atividadelistatasksspring.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.tasklist.atividadelistatasksspring.entities.Task;
import com.tasklist.atividadelistatasksspring.dtos.TaskDTO;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/tasks")
public class TaskController {
  @Autowired()
  private final TaskService taskService;

  public TaskController(TaskService taskService) {
    this.taskService = taskService;
  }

  @GetMapping
  public List<Task> getAllPlayers() {
    return taskService.getAllTasks();
  }
}

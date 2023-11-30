package com.tasklist.atividadelistatasksspring.controllers;

import com.tasklist.atividadelistatasksspring.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

  @GetMapping()
  public ResponseEntity<List<Task>> getAllTasks() {
    var tasks = taskService.getAllTasks();

    if(tasks.size() > 0){
      return ResponseEntity.ok(tasks);
    }

    return ResponseEntity.noContent().build();
  }

  @GetMapping("{id}")
  public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
    var task = taskService.getTaskById(id);

    if(task == null){
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(task);
  }

  @PostMapping()
  public void createTask(@RequestBody TaskDTO taskDTO) {
    taskService.createTask(taskDTO);
  }

  @PutMapping("{id}/status")
  public ResponseEntity<TaskDTO> completedTask(@PathVariable Long id) {
    var markedTask = taskService.chageStatus(id);

    if (markedTask != null) {
      return ResponseEntity.ok(markedTask);
    }

    return ResponseEntity.notFound().build();
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> DeleteTask(@PathVariable Long id) {
    taskService.deleteTask(id);

    return ResponseEntity.ok().build();
  }
}

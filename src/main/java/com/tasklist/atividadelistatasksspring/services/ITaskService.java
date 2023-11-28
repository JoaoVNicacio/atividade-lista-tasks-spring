package com.tasklist.atividadelistatasksspring.services;

import com.tasklist.atividadelistatasksspring.dtos.TaskDTO;
import com.tasklist.atividadelistatasksspring.entities.Task;

import java.util.List;

public interface ITaskService {
  List<Task> getAllTasks();
  Task getTaskById(long id);
  Task createTask(TaskDTO taskDTO);
  TaskDTO chageStatus(Long id);
  void deleteTask(Long id);
}

package com.tasklist.atividadelistatasksspring.services;

import com.tasklist.atividadelistatasksspring.dtos.TaskDTO;
import com.tasklist.atividadelistatasksspring.entities.Task;
import java.util.List;

import com.tasklist.atividadelistatasksspring.repositories.ITaskRepository;

public class TaskService implements ITaskService{
  private final ITaskRepository taskRepository;

  public TaskService(ITaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  @Override
  public List<Task> getAllTasks() {
    return taskRepository.findAll();
  }

  @Override
  public Task getTaskById(long id) {
    var task = taskRepository.findById(id);

    if(task.isPresent()){
      return task.get();
    }

    return null;
  }

  @Override
  public Task createTask(TaskDTO taskDTO) {
    var newTask = new Task();

    newTask.setTaskDescription(taskDTO.description());
    newTask.setIsCompleted(false);

    return taskRepository.save(newTask);
  }

  @Override
  public TaskDTO chageStatus(Long id) {
    var existingTaskOptional = taskRepository.findById(id);

    if (existingTaskOptional.isPresent()) {
      Task existingTask = existingTaskOptional.get();

      existingTask.setIsCompleted(!getIsCompleted());
      taskRepository.save(existingTask);

      return new TaskDTO(existingTask.getDescription(), existingTask.getIsCompleted());
    }

    return null;
  }

  @Override
  public void deleteTask(Long id) {
    var existingTaskOptional = taskRepository.findById(id);
    var existingTask = existingTaskOptional.get();

    taskRepository.delete(existingTask);
  }
}

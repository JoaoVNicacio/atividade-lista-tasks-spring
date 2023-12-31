package com.tasklist.atividadelistatasksspring.services;

import com.tasklist.atividadelistatasksspring.dtos.TaskDTO;
import com.tasklist.atividadelistatasksspring.entities.Task;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tasklist.atividadelistatasksspring.repositories.ITaskRepository;

@Service
public class TaskService implements ITaskService{
  private final ITaskRepository taskRepository;

  @Autowired
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

    newTask.setTaskDescription(taskDTO.taskDescription());
    newTask.setCompleted(false);

    return taskRepository.save(newTask);
  }

  @Override
  public TaskDTO chageStatus(Long id) {
    var existingTaskOptional = taskRepository.findById(id);

    if (existingTaskOptional.isPresent()) {
      var existingTask = existingTaskOptional.get();

      existingTask.setCompleted(!existingTask.isCompleted());
      taskRepository.save(existingTask);

      return new TaskDTO(existingTask.getTaskDescription(), existingTask.isCompleted());
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

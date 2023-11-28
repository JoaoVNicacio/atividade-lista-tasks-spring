package com.tasklist.atividadelistatasksspring.repositories;

import com.tasklist.atividadelistatasksspring.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ITaskRepository extends JpaRepository<Task, Long> {
  List<Task> findAll();
  Task save(Task task);
  Optional<Task> findById(Long id);
  void delete(Task task);
}

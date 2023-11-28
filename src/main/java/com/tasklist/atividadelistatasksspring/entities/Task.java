package com.tasklist.atividadelistatasksspring.entities;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name= "Tasks")
public class Task {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private boolean isCompleted;
  private String taskDescription;
}

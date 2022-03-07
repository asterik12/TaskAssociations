package com.TaskAssociationsBackend.TaskAssociationsBackend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TaskAssociationsBackend.TaskAssociationsBackend.Models.Task;


@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{

}

package com.jwtlogin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jwtlogin.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}

package com.hamadiddi.task_management_system.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import com.hamadiddi.task_management_system.entity.Task;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUserId(Long userId);
    List<Task> findByUserIdAndStatus(Long userId, String status);
    List<Task> findByUserIdAndPriority(Long userId, String priority);
}
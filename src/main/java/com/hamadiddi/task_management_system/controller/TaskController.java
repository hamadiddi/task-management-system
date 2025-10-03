package com.hamadiddi.task_management_system.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.hamadiddi.task_management_system.dto.TaskDto;
import com.hamadiddi.task_management_system.entity.Task;
import com.hamadiddi.task_management_system.entity.User;
import com.hamadiddi.task_management_system.repository.UserRepository;
import com.hamadiddi.task_management_system.service.TaskService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "http://localhost:3000")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<TaskDto> getTasks(Authentication authentication, @RequestParam(required = false) String status, @RequestParam(required = false) String priority) {
        Long userId = getUserIdFromAuth(authentication);
        List<Task> tasks;
        if (status != null) {
            tasks = taskService.getTasksByStatus(userId, status);
        } else if (priority != null) {
            tasks = taskService.getTasksByPriority(userId, priority);
        } else {
            tasks = taskService.getTasksByUser(userId);
        }
        return tasks.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @PostMapping
    public TaskDto createTask(@RequestBody TaskDto taskDto, Authentication authentication) {
        Task task = convertToEntity(taskDto);
        task.setUserId(getUserIdFromAuth(authentication));
        Task savedTask = taskService.createTask(task);
        return convertToDto(savedTask);
    }

    @PutMapping("/{id}")
    public TaskDto updateTask(@PathVariable Long id, @RequestBody TaskDto taskDto, Authentication authentication) {
        Long userId = getUserIdFromAuth(authentication);
        Task existing = taskService.getTaskById(id); // Assumes getTaskById exists in TaskService
        if (!existing.getUserId().equals(userId)) {
            throw new RuntimeException("Unauthorized");
        }
        Task taskDetails = convertToEntity(taskDto);
        Task updatedTask = taskService.updateTask(id, taskDetails);
        return convertToDto(updatedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id, Authentication authentication) {
        Long userId = getUserIdFromAuth(authentication);
        Task existing = taskService.getTaskById(id);
        if (!existing.getUserId().equals(userId)) {
            throw new RuntimeException("Unauthorized");
        }
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    private TaskDto convertToDto(Task task) {
        TaskDto dto = new TaskDto();
        dto.setTaskId(task.getTaskId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setStatus(task.getStatus());
        dto.setPriority(task.getPriority());
        dto.setDueDate(task.getDueDate());
        return dto;
    }

    private Task convertToEntity(TaskDto dto) {
        Task task = new Task();
        task.setTaskId(dto.getTaskId());
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStatus(dto.getStatus() != null ? dto.getStatus() : "TODO");
        task.setPriority(dto.getPriority() != null ? dto.getPriority() : "MEDIUM");
        task.setDueDate(dto.getDueDate());
        return task;
    }

    private Long getUserIdFromAuth(Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getUserId();
    }
}
package com.hamadiddi.task_management_system.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class TaskDto {
    private Long taskId;
    private String title;
    private String description;
    private String status;
    private String priority;
    private LocalDateTime dueDate;
}
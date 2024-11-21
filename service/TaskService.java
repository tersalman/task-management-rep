package com.example.task_management_system.service;

import com.example.task_management_system.model.Comment;
import com.example.task_management_system.model.dto.TaskDto;
import com.example.task_management_system.model.request.TaskRequest;

import java.util.List;
import java.util.Set;

public interface TaskService {


    List<TaskDto> getTask();
    TaskDto getTask(Long id);
    TaskDto getTask(String title);
    TaskDto createTask(TaskRequest task);
    TaskDto updateTask(TaskRequest task);
    String deleteTask(String title);
    void deleteTaskById(Long id);


}

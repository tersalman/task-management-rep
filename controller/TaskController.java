package com.example.task_management_system.controller;

import com.example.task_management_system.auth.AuthenticationService;
import com.example.task_management_system.config.JwtService;
import com.example.task_management_system.model.dto.TaskDto;
import com.example.task_management_system.model.request.TaskRequest;
import com.example.task_management_system.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;
    private final AuthenticationService authenticationService;
    private final JwtService jwtService;
    @GetMapping
    public ResponseEntity<List<TaskDto>> getTask() {
        return ResponseEntity.ok(taskService.getTask());
    }
    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTask(id));
    }
    @GetMapping("/title/{title}")
    public ResponseEntity<TaskDto> getTaskByTitle(@PathVariable String title) {
        return ResponseEntity.ok(taskService.getTask(title));
    }
    @PostMapping
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskRequest task) {

        return ResponseEntity.ok(taskService.createTask(task));
    }
    @PutMapping
    public ResponseEntity<TaskDto> updateTask(@RequestBody TaskRequest task) {

        return ResponseEntity.ok(taskService.updateTask(task));
    }
    @DeleteMapping("{title}")
    public ResponseEntity deleteTask(@PathVariable String title) {
        taskService.deleteTask(title);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/id/{id}")
    public ResponseEntity deleteTask(@PathVariable Long id) {
        taskService.deleteTaskById(id);
        return ResponseEntity.ok().build();
    }
}

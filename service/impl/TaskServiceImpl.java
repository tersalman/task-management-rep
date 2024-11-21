package com.example.task_management_system.service.impl;

import com.example.task_management_system.model.Comment;
import com.example.task_management_system.model.Task;
import com.example.task_management_system.model.User;
import com.example.task_management_system.model.dto.CommentDto;
import com.example.task_management_system.model.dto.TaskDto;
import com.example.task_management_system.model.mapper.TaskMapper;
import com.example.task_management_system.model.request.CommentRequest;
import com.example.task_management_system.model.request.TaskRequest;
import com.example.task_management_system.repository.TaskRepository;
import com.example.task_management_system.repository.UserRepository;
import com.example.task_management_system.service.CommentService;
import com.example.task_management_system.service.TaskService;
import com.example.task_management_system.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
@Slf4j
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
//    private final CommentService commentService;
    private final TaskRepository taskRepository;
    private final UserService userService;
    private final TaskMapper taskMapper;

    /**
     * get all tasks in database
     * @return List of all tasks in database in Dto format
     */
    @Override
    public List<TaskDto> getTask() {
        List<Task> all = taskRepository.findAll();
        return taskMapper.convertToDtoList(all);
    }
    /**
     * getting the task by title
     * @param id The Long variable with id of wanted task
     * @return A TaskDto representing the task
     * @throws IllegalArgumentException if task with provided id not found
     */
    @Transactional
    @Override
    public TaskDto getTask(Long id) {
        Task task = taskRepository.findById(id).get();
        if (task == null) {
            throw new IllegalArgumentException("Task with this id does not exist.");
        }
        return taskMapper.convertToDto(task);
    }

    /**
     * getting the task by title
     * @param title The String variable with name of wanted task
     * @return A TaskDto representing the task
     * @throws IllegalArgumentException if task with provided title not found
     */
    @Override
    public TaskDto getTask(String title) {
        Task task = taskRepository.findByTitle(title);
        if (task == null) {
            throw new IllegalArgumentException("Task with this title does not exist.");
        }
        return taskMapper.convertToDto(task);
    }
    /**
     * Creating task based on the provided TaskRequest.
     *
     * This method retrieves the task by its title and author name, updates its fields
     * with the new values from the TaskRequest, and saves the changes to the database.
     *
     * @param task The TaskRequest object containing the updated task information.
     *             It should include the task's title, description, executor name,
     *             priority, status, and the email of the author.
     * @return A TaskDto object representing the updated task.
     * @throws IllegalArgumentException If no task is found with the given title and author.
     */
    @Override
    public TaskDto createTask(TaskRequest task) {

        String authorName = userService.getAuthorNameByEmail(task.getEmail());

        Task byTitleAndAuthorName = taskRepository.findByTitleAndAuthorName(task.getTitle(), authorName);
        if (byTitleAndAuthorName!= null) {
            throw new IllegalArgumentException("Task with this title and author already exists.");
        }
        Task task1 = Task.builder()
                .title(task.getTitle())
                .description(task.getDescription())
                .authorName(authorName)
                .executorName(task.getExecutorName())
                .priority(task.getPriority())
                .status(task.getStatus())
                .build();
        taskRepository.save(task1);

        return taskMapper.convertToDto(task1);
    }

        /**
     * Updates an existing task based on the provided TaskRequest.
     * 
     * This method retrieves the task by its title and author name, updates its fields
     * with the new values from the TaskRequest, and saves the changes to the database.
     * 
     * @param task The TaskRequest object containing the updated task information.
     *             It should include the task's title, description, executor name,
     *             priority, status, and the email of the author.
     * @return A TaskDto object representing the updated task.
     * @throws IllegalArgumentException If no task is found with the given title and author.
     */
    @Override
    public TaskDto updateTask(TaskRequest task) {

        String authorName = userService.getAuthorNameByEmail(task.getEmail());

        Task task1 = taskRepository.findByTitleAndAuthorName(task.getTitle(), authorName);
        if (task1 == null) {
            throw new IllegalArgumentException("Task with this title and author does not exist.");
        } else {
            task1.setTitle(task.getTitle());
            task1.setDescription(task.getDescription());
            task1.setExecutorName(task.getExecutorName());
            task1.setPriority(task.getPriority());
            task1.setStatus(task.getStatus());
            taskRepository.save(task1);
        }
        return taskMapper.convertToDto(task1);
    }

    /**
     * delete a task from database by title
     * @param title String title of task to delete
     * @return String title of deleted task
     */
    @Transactional
    @Override
    public String deleteTask(String title) {
        Task task = taskRepository.findByTitle(title);

        if (task == null) {
            log.error(" have a {}, because there is no task with this title", NullPointerException.class.getName());
        } else {
            String titleOfDeletedTask = task.getTitle();
            taskRepository.delete(task);
            return titleOfDeletedTask;
        }
        return null;
    }

    /**
     * delete a task by Long id
     * @param id Long id of task to delete
     */
    @Override
    public void deleteTaskById(Long id) {
        taskRepository.deleteById(id);
    }



}

package com.example.task_management_system.repository;

import com.example.task_management_system.model.Comment;
import com.example.task_management_system.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;
import java.util.Set;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @NonNull
    Optional<Task> findById(Long id);

    Task findByTitle(String title);

    //    Set<Comment> findCommentsByTaskAndAuthorName(Task task, String authorName);
    Task findByTitleAndAuthorName(String title, String authorName);
}

package com.example.task_management_system.repository;

import com.example.task_management_system.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Set<Comment> findByAuthorName(String authorName);
    Comment findByAuthorNameAndContent(String authorName, String content);
}

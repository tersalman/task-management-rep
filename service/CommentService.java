package com.example.task_management_system.service;

import com.example.task_management_system.model.Comment;
import com.example.task_management_system.model.dto.CommentDto;
import com.example.task_management_system.model.request.CommentRequest;

import java.util.List;
import java.util.Set;

public interface CommentService {
    Set<CommentDto> getComment(String title);
    CommentDto createComment(CommentRequest comment);
    CommentDto updateComment(CommentRequest comment);
    void deleteComment(Long id);
}

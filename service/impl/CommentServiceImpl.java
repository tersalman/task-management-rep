package com.example.task_management_system.service.impl;

import com.example.task_management_system.model.Comment;
import com.example.task_management_system.model.Task;
import com.example.task_management_system.model.User;
import com.example.task_management_system.model.dto.CommentDto;
import com.example.task_management_system.model.dto.TaskDto;
import com.example.task_management_system.model.mapper.CommentMapper;
import com.example.task_management_system.model.mapper.TaskMapper;
import com.example.task_management_system.model.request.CommentRequest;
import com.example.task_management_system.repository.CommentRepository;
import com.example.task_management_system.repository.TaskRepository;
import com.example.task_management_system.repository.UserRepository;
import com.example.task_management_system.service.CommentService;
import com.example.task_management_system.service.TaskService;
import com.example.task_management_system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final TaskService taskService;
    private final CommentMapper commentMapper;
    private final TaskMapper taskMapper;
    private final UserService userService;

    /**
     * get all comments in chose task
     * @param title String param with title of needed task
     * @return Set with all comments in selected task
     */
    @Override
    public Set<CommentDto> getComment(String title) {
        TaskDto task = taskService.getTask(title);

        return task.getComments();
    }

        /**
     * Creates a new comment for a given task.
     *
     * @param comment The request containing the comment's author's email, task title, and comment content.
     * @return The newly created comment converted to a DTO.
     * @throws IllegalArgumentException if a comment with the same content and author already exists.
     */
    @Override
    public CommentDto createComment(CommentRequest comment) {

        TaskDto taskDto = taskService.getTask(comment.getTaskTitle());
        Task taskEntity = taskMapper.convertToEntity(taskDto);


        String authorName = userService.getAuthorNameByEmail(comment.getEmail());
        Comment byAuthorNameAndContent = commentRepository.findByAuthorNameAndContent(authorName, comment.getContent());

        if (byAuthorNameAndContent != null) {
            throw new IllegalArgumentException("Comment with this content and author already exists.");
        }

        Comment build = Comment.builder().authorName(authorName)
                .content(comment.getContent())
                .build();

        return commentMapper.convertToDto(commentRepository.save(build));
    }

        /**
     * Updates an existing comment with the provided content.
     *
     * @param comment The request containing the comment's author's email, task title, and new comment content.
     * @return The updated comment converted to a DTO.
     * @throws IllegalArgumentException if a comment with the same content and author is not found.
     */
    @Override
    public CommentDto updateComment(CommentRequest comment) {
        String authorName = userService.getAuthorNameByEmail(comment.getEmail());
        Comment commentEntity = commentRepository.findByAuthorNameAndContent(authorName, comment.getContent());
        if (commentEntity == null) {
            throw new IllegalArgumentException("Comment with this content and author not found.");
        }else {
            commentEntity.setContent(comment.getContent());
        }
        return commentMapper.convertToDto(commentEntity);
    }

    /**
     * delete a comment from database by id
     * @param id Long id of the comment
     */
    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}

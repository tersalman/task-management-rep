package com.example.task_management_system.controller;

import com.example.task_management_system.model.dto.CommentDto;
import com.example.task_management_system.model.request.CommentRequest;
import com.example.task_management_system.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/task/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/{title}")
    public ResponseEntity<Set<CommentDto>> getComments(@PathVariable String title) {
        return ResponseEntity.ok().body(commentService.getComment(title));
    }

    @PostMapping
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentRequest comment) {
        return ResponseEntity.ok().body(commentService.createComment(comment));
    }

    @PutMapping
    public ResponseEntity<CommentDto> updateComment(@RequestBody CommentRequest comment) {
        return ResponseEntity.ok().body(commentService.createComment(comment));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity deleteComment( @PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok().build();
    }



}

package com.example.task_management_system.model.mapper;

import com.example.task_management_system.model.Comment;
import com.example.task_management_system.model.dto.CommentDto;
import com.example.task_management_system.repository.UserRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentMapper extends BaseMapper<Comment, CommentDto>{


    @Override
    public Comment convertToEntity(CommentDto dto, Object... args) {
        Comment entity = new Comment();
        if (dto != null) {
            BeanUtils.copyProperties(dto,entity);
        }
        return entity;
    }

    @Override
    public CommentDto convertToDto(Comment entity, Object... args) {
        CommentDto dto = new CommentDto();
        if (entity != null) {
            BeanUtils.copyProperties(entity,dto);
        }
        return dto;
    }
}

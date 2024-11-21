package com.example.task_management_system.model.mapper;

import com.example.task_management_system.model.Task;
import com.example.task_management_system.model.dto.TaskDto;
import com.example.task_management_system.model.request.TaskRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskMapper extends BaseMapper<Task, TaskDto>{

    private final CommentMapper commentMapper;
    @Override
    public Task convertToEntity(TaskDto dto, Object... args) {
        Task entity = new Task();
        if (dto!=null) {
            BeanUtils.copyProperties(dto,entity,"comment");
            entity.setComments(commentMapper.convertToEntitySet(dto.getComments()));
        }
        return entity;
    }

    @Override
    public TaskDto convertToDto(Task entity, Object... args) {
        TaskDto dto = new TaskDto();
        if (entity!=null) {
            BeanUtils.copyProperties(entity,dto,"comment");
            dto.setComments(commentMapper.convertToDtoSet(entity.getComments()));
        }
        return dto;
    }
}

package cc.login.tasksystem.controllers.dto;

public record GetTaskResponseDto(
        long id,
        String titleTask,
        String description
) {
}

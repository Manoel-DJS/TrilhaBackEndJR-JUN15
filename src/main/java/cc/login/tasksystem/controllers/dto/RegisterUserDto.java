package cc.login.tasksystem.controllers.dto;


import cc.login.tasksystem.models.UserRole;

public record RegisterUserDto (String username, String password, UserRole role){
}

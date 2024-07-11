package cc.login.tasksystem.controllers;

import cc.login.tasksystem.controllers.dto.CreateTaskDto;
import cc.login.tasksystem.models.User;
import cc.login.tasksystem.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/end/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/{userId}/tasks")
    public ResponseEntity<User> createTaskUser(@PathVariable("userId") String userId,
                                               @RequestBody CreateTaskDto createTaskDto){
        taskService.createTask(userId, createTaskDto);
        return ResponseEntity.ok().build();
    }
}

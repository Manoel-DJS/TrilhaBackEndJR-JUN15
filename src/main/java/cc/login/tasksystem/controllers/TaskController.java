package cc.login.tasksystem.controllers;

import cc.login.tasksystem.controllers.dto.CreateTaskDto;
import cc.login.tasksystem.controllers.dto.TaskRequestDto;
import cc.login.tasksystem.controllers.dto.TaskResponseDto;
import cc.login.tasksystem.controllers.dto.UpdateTaskDto;
import cc.login.tasksystem.models.Task;
import cc.login.tasksystem.models.User;
import cc.login.tasksystem.service.TaskService;
import cc.login.tasksystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/end/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // AuthTask
    @PostMapping("/authTask")
    public ResponseEntity<TaskResponseDto> createAuthTask (@RequestBody TaskRequestDto requestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createAuthTask(requestDto));
    }

    @PostMapping("/{userId}")
    public ResponseEntity<User> createTaskUser(@PathVariable("userId") String userId,
                                               @RequestBody CreateTaskDto createTaskDto){
        taskService.createTask(userId, createTaskDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{userId}/tasks")
    public ResponseEntity<Void> updateTaskUserById(@PathVariable("userId") String userId, Long taskId,
                                                   @RequestBody UpdateTaskDto updateTaskDto){
        taskService.updateTaskUserById(userId, taskId, updateTaskDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userId}/tasks")
    public ResponseEntity<Void> deleteTaskUserById(@PathVariable("userId")
                                                       String userId, Long taskId){
        taskService.deleteTaskUserById(userId, taskId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDto>> findAllTasks(){
        return ResponseEntity.ok().body(taskService.findAllTasks());
    }
}

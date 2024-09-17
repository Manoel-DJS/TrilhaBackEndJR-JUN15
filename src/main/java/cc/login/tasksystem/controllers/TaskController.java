package cc.login.tasksystem.controllers;

import cc.login.tasksystem.controllers.dto.*;
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

    // Update AuthTask 'OK'
    @PutMapping("/authTask/{idTask}")
    public ResponseEntity<TaskResponseDto> updateAuthTask(@RequestBody TaskRequestDto requestDto, @PathVariable long idTask){
        return ResponseEntity.status(HttpStatus.OK).body(taskService.updateAuthTask(requestDto, idTask));
    }

    // DeleteAuthTask
    @DeleteMapping("authTask/{idTask}")
    public ResponseEntity<Void> deleteAuthTask(@PathVariable long idTask){
        taskService.deleteAuthTask(idTask);
        return ResponseEntity.ok().build();
    }

    // Get AuthTask
    @GetMapping("authTask/")
    public ResponseEntity<List<GetTaskResponseDto>> findTaskAuthUser(){
        return ResponseEntity.ok().body(taskService.findTaskAuthUser());
    }


    @PostMapping("/{userId}")
    public ResponseEntity<User> createTaskUser(@PathVariable("userId") String userId,
                                               @RequestBody CreateTaskDto createTaskDto){
        taskService.createTask(userId, createTaskDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateTaskUserById(@PathVariable("userId") String userId, Long taskId,
                                                   @RequestBody UpdateTaskDto updateTaskDto){
        taskService.updateTaskUserById(userId, taskId, updateTaskDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userId}")
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

package cc.login.tasksystem.service;

import cc.login.tasksystem.controllers.dto.CreateTaskDto;
import cc.login.tasksystem.controllers.dto.UpdateTaskDto;
import cc.login.tasksystem.models.Task;
import cc.login.tasksystem.repository.TaskRepository;
import cc.login.tasksystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    public void createTask(String userId, CreateTaskDto createTaskDto) {
        var user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new RuntimeException("ID não encontrado"));

        // DTO -> Entity
        var taskUser = new Task();
        taskUser.setUser(user);
        taskUser.setTitleTask(createTaskDto.titleTask());
        taskUser.setDescription(createTaskDto.description());

        var TaskCreated = taskRepository.save(taskUser);
    }

    public void deleteTaskUserById(String userId, Long taskId) {
        var userExists = userRepository.findById(UUID.fromString(userId));
        var taskExists = taskId;

        if(userExists.isPresent() && taskExists != null){
            var deletedTask = new Task();
            deletedTask.setUser(userExists.get());
            deletedTask.setId(taskExists);
            taskRepository.delete(deletedTask);
        }
    }

    public void updateTaskUserById(String userId, Long taskId, UpdateTaskDto updateTaskDto) {
        var user = userRepository.findById(UUID.fromString(userId));
        var task = taskRepository.findById(taskId);

        if(user.isPresent() && task.isPresent()){

            var useri = user.get();
            var newUserTask = task.get();

            if(updateTaskDto.titleTask() != null){
                newUserTask.setTitleTask(updateTaskDto.titleTask());
            }
            if(updateTaskDto.description() != null){
                newUserTask.setDescription(updateTaskDto.description());
            }

            taskRepository.save(newUserTask);
        }
    }
}

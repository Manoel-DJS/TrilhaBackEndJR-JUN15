package cc.login.tasksystem.service;

import cc.login.tasksystem.controllers.dto.*;
import cc.login.tasksystem.models.Task;
import cc.login.tasksystem.models.User;
import cc.login.tasksystem.repository.TaskRepository;
import cc.login.tasksystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    public User getTaskById(String userId){
        return userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new RuntimeException("ID não encontrado"));
    }

    // TASK AUTHENTICADIX
    public TaskResponseDto createAuthTask(TaskRequestDto requestDto){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();

        User user = (User) userRepository.findByUsername(username);

        Task newTask = new Task();
        newTask.setTitleTask(requestDto.titleTask());
        newTask.setDescription(requestDto.description());
        newTask.setUser(user);

        //return this.toDto(taskRepository.save(newTask));
        return toDto(taskRepository.save(newTask));
    }

    // update TaskAuth 'OK'

    public TaskResponseDto updateAuthTask(TaskRequestDto requestDto, long id){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();

        User user = (User) userRepository.findByUsername(username);

        var task = taskRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Tarefa com ID " + id + " não encontrada."));

        if(user != null && task.getUser().getUserid().equals(user.getUserid())){

            // var newUserTask = task.getUser();

            if(requestDto.titleTask() != null){
                task.setTitleTask(requestDto.titleTask());
            }
            if(requestDto.description() != null){
                // newUserTask.setDescription(requestDto.description());
                task.setDescription(requestDto.description());
            }

            return toDto(taskRepository.save(task));
        } else {
            throw new SecurityException("Você não tem permissão para alterar esta tarefa.");
        }
    }

    public void deleteAuthTask(long taskId){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        User user = (User) userRepository.findByUsername(username);

        var task = taskRepository.findById(taskId)
                .orElseThrow(() -> new NoSuchElementException("Tarefa com ID " + taskId + " não encontrada."));

        if(user != null && task.getUser().getUserid().equals(user.getUserid())){
            var deletedTask = new Task();
            deletedTask.setUser(user);
            deletedTask.setId(taskId);

            taskRepository.delete(deletedTask);
        } else {
            throw new SecurityException("Você não tem permissão para alterar esta tarefa.");
        }
    }

    public List<TaskResponseDto> findAllTasks(){
        return taskRepository.findAll().stream().map(this::toDto).toList();
    }

    public List<GetTaskResponseDto> findTaskAuthUser(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();

        User user = (User) userRepository.findByUsername(username);

        if (user == null) {
            throw new NoSuchElementException("Usuário não encontrado.");
        }

        // Busca todas as tarefas e filtra pelas que pertencem ao usuário logado
        List<Task> userTasks = taskRepository.findAll()
                .stream()
                .filter(task -> task.getUser().getUserid().equals(user.getUserid())) // Filtra por ID do usuário
                .toList();

        // Converte a lista de tarefas para DTOs e retorna
        return userTasks.stream()
                .map(this::toDtu) // Conversão para TaskResponseDto
                .collect(Collectors.toList());
    }

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

    private TaskResponseDto toDto(Task task){
        return new TaskResponseDto(task.getTitleTask(), task.getDescription()) ;
    }

    private GetTaskResponseDto toDtu(Task task){
        return new GetTaskResponseDto(task.getId(), task.getTitleTask(), task.getDescription());
    }
}

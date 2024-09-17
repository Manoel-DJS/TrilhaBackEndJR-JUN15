package cc.login.tasksystem.controllers;


import cc.login.tasksystem.controllers.dto.CreateUserDto;
import cc.login.tasksystem.models.User;
import cc.login.tasksystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/end/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody CreateUserDto createUserDto){
        var userId = userService.createUser(createUserDto);

        return ResponseEntity.created(URI.create("/end/users" + userId.toString())).build();
    }

    @GetMapping
    public ResponseEntity<List<User>> getUserAll(){
        var users = userService.ListUsers();
        return ResponseEntity.ok(users);
    }

    // Teste
    @GetMapping("Testing")
    public ResponseEntity<String> getUser(){
        return ResponseEntity.ok("OK!");
    }
}

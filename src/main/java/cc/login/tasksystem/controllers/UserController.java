package cc.login.tasksystem.controllers;


import cc.login.tasksystem.controllers.dto.CreateUserDto;
import cc.login.tasksystem.models.User;
import cc.login.tasksystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/end/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getUserAll(){
        var users = userService.ListUsers();
        return ResponseEntity.ok(users);
    }

    // Teste
    @GetMapping("Testing")
    public ResponseEntity<String> getOK(){
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "application/json").body("{\"message\":\"OK!\"}");
    }
}

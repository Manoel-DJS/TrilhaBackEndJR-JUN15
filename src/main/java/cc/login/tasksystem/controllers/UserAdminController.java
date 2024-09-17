package cc.login.tasksystem.controllers;

import cc.login.tasksystem.controllers.dto.UpdateUserDto;
import cc.login.tasksystem.models.User;
import cc.login.tasksystem.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin777")
@Tag(name = "ADMIN-777", description = "userAdmin")
public class UserAdminController {
    @Autowired
    private UserService userService;
    @GetMapping
    public ResponseEntity<List<User>> getUserAll(){
        var users = userService.ListUsers();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteById(@PathVariable("userId") String userId){
        userService.deleteById(userId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUserById(@PathVariable("userId") String userId,
                                               @RequestBody UpdateUserDto updateUserDto){
        userService.updateUserById(userId, updateUserDto);
        return ResponseEntity.noContent().build();
    }


}

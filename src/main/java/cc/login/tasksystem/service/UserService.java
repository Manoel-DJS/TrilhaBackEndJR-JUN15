package cc.login.tasksystem.service;


import cc.login.tasksystem.controllers.dto.CreateUserDto;
import cc.login.tasksystem.models.User;
import cc.login.tasksystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public UUID createUser(CreateUserDto userDto){

        // DTO -> ENTITY
        var entity = new User();
        entity.setUserid(UUID.randomUUID());
        entity.setUsername(userDto.username());
        entity.setPassword(userDto.password());


        var userSaved = userRepository.save(entity);

        return userSaved.getUserid();
    }

    public List<User> ListUsers(){
        return userRepository.findAll();
    }

    public void deleteById(String userId){
        var id = UUID.fromString(userId);
        var userExists = userRepository.existsById(id);

        if(userExists){
            userRepository.deleteById(id);
        }
    }

}

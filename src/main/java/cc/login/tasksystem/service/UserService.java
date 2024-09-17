package cc.login.tasksystem.service;


import cc.login.tasksystem.controllers.dto.CreateUserDto;
import cc.login.tasksystem.controllers.dto.UpdateUserDto;
import cc.login.tasksystem.models.User;
import cc.login.tasksystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    public void updateUserById(String userId, UpdateUserDto updateUserDto){
        var id = UUID.fromString(userId);
        var userEntity = userRepository.findById(id);

        String encryptedPassword = new BCryptPasswordEncoder().encode(updateUserDto.password());

        if(userEntity.isPresent()){
            var user = userEntity.get();

            if (updateUserDto.username() != null){
                user.setUsername(updateUserDto.username());
            }
            if (updateUserDto.password() != null){
                user.setPassword(encryptedPassword);
            }

            userRepository.save(user);
        }
    }


    public void deleteById(String userId){
        var id = UUID.fromString(userId);
        var userExists = userRepository.existsById(id);

        if(userExists){
            userRepository.deleteById(id);
        }
    }

}

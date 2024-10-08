package cc.login.tasksystem.controllers;

import cc.login.tasksystem.controllers.dto.AuthenticationDto;
import cc.login.tasksystem.controllers.dto.LoginResponseDto;
import cc.login.tasksystem.controllers.dto.RegisterUserDto;
import cc.login.tasksystem.models.User;
import cc.login.tasksystem.repository.UserRepository;
import cc.login.tasksystem.service.TokenService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@Tag(name = "Authentication", description = "Login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDto authenticationDto){
        var usernamePassword = new UsernamePasswordAuthenticationToken(authenticationDto.username(), authenticationDto.password());
        var auth = authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDto(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterUserDto registerUserDto){
        if(this.userRepository.findByUsername(registerUserDto.username()) != null){
            return ResponseEntity.badRequest().build();
        }
            String encryptedPassword = new BCryptPasswordEncoder().encode(registerUserDto.password());
        // New User
            User newUser = new User();
            newUser.setUsername(registerUserDto.username());
            newUser.setPassword(encryptedPassword);
            newUser.setRole(registerUserDto.role());

        this.userRepository.save(newUser);

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "application/json").body("{\"message\":\"Usuário Criado Com Sucesso!\"}");
    }
}

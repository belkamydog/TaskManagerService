package ru.task.TaskManager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import ru.task.TaskManager.models.User;
import ru.task.TaskManager.service.AuthService;
import ru.task.TaskManager.service.UserService;

@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody User user) {
        try {
            authService.registration(user);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/authorisation")
    public ResponseEntity<?> authentication(@RequestHeader (value = "Authorization") String authHeader) {
        try {
            String token = authService.authorisation(authHeader);
            return ResponseEntity.status(HttpStatus.OK).body(token);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}

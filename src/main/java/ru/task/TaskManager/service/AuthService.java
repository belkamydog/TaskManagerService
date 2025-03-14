package ru.task.TaskManager.service;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.task.TaskManager.models.User;
import ru.task.TaskManager.repository.UserRepository;
import ru.task.TaskManager.security.service.JwtService;

import java.util.Base64;

@Service
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;


    @Autowired
    public AuthService(PasswordEncoder passwordEncoder, JwtService jwtService, UserRepository userRepository, AuthenticationManager authenticationManager) {
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
    }

    public void registration(User user) throws Exception {
        User foundUser = userRepository.findByEmail(user.getEmail());
        if (foundUser != null) throw new Exception("User is already exists");
        user.setPassword(encodePassword(user.getPassword()));
        userRepository.save(user);
    }

    public String authorisation(String authHeader) throws Exception {
        User user = encodeUserData(authHeader);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        return createJwtToken(user);
    }

    public void checkAccess(String token, Long userId) throws Exception {
        token = token.substring(7);
        Claims claims = jwtService.parseToken(token);
        Long id = claims.get("id", Long.class);
        if (!userId.equals(id)) throw new Exception("Access Deny");
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public void checkJwtToken(String authHeader) throws Exception {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            jwtService.parseToken(token);
        }
        else throw new Exception("Invalid token");
    }

    private String createJwtToken(User user) {
        User foundUser  = userRepository.findByEmail(user.getEmail());
        return jwtService.generateToken(foundUser);
    }

    private User encodeUserData(String authHeader) {
        String base64Credentials = authHeader.substring("Basic ".length()).trim();
        byte[] credentials = Base64.getDecoder().decode(base64Credentials);
        String credentialsString = new String(credentials);
        String[] credentialsArray = credentialsString.split(":");
        String email = credentialsArray[0];
        String password = credentialsArray[1];
        return new User(password, email);
    }

}

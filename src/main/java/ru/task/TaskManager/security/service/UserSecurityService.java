package ru.task.TaskManager.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.task.TaskManager.models.User;
import ru.task.TaskManager.models.UserPrincipal;
import ru.task.TaskManager.repository.UserRepository;

@Service
public class UserSecurityService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserSecurityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {throw new UsernameNotFoundException(email);}
        return new UserPrincipal(user);
    }
}

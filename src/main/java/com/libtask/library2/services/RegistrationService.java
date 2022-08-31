package com.libtask.library2.services;

import com.libtask.library2.dto.RegistrationRequest;
import com.libtask.library2.entities.Role;
import com.libtask.library2.entities.User;
import com.libtask.library2.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public User register(RegistrationRequest request) {
        User newUser = new User (
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword(),
                Role.USER
        );
        if (userRepository.existsByEmailIgnoreCase(newUser.getEmail())) {
            throw new IllegalStateException("User with this email is already signed up");
        }
        signUpUser(newUser);
        return newUser;
    }

    public void signUpUser(User user) {
            String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            userRepository.save(user);
    }
}

package com.libtask.library2.services;

import com.libtask.library2.dto.RegistrationRequest;
import com.libtask.library2.entities.Role;
import com.libtask.library2.entities.User;
import com.libtask.library2.exceptions.RegistrationComplianceException;
import com.libtask.library2.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public User register(RegistrationRequest request) throws RegistrationComplianceException {
        User newUser = new User (
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword(),
                Role.USER
        );
        if (userRepository.existsByEmailIgnoreCase(newUser.getEmail())) {
            throw new RegistrationComplianceException("User with this email is already signed up");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(newUser.getPassword());
        newUser.setPassword(encodedPassword);
        userRepository.save(newUser);
        return newUser;
    }
}

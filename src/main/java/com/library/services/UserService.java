package com.library.services;

import com.library.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
     private final UserRepository userRepository;
}

package com.libtask.library2.services;


import com.libtask.library2.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
     private final UserRepository userRepository;
}

package com.libtask.library2.services;

import com.libtask.library2.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
     private final UserRepository userRepository;

     @Override
     public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
          return userRepository.findByEmailIgnoreCase(email)
                  .orElseThrow(() -> new UsernameNotFoundException("User not found"));
     }
}

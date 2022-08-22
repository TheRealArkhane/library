package com.libtask.library2.services;

import com.libtask.library2.entities.User;
import com.libtask.library2.repositories.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
     @NonNull
     private final UserRepository userRepository;

     public List<User> showAllUsers() {
          return userRepository.showAllUsers();
     }

     public User getUserById(Long id) {
          return userRepository.findById(id).get();
     }

     public void changeEmail(String email, User user) {
          userRepository.changeEmail(email, user.getId());
     }

     @Override
     public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
          return userRepository.findByEmail(email).orElseThrow(()
                  -> new UsernameNotFoundException("Пользователь не найден"));
     }
}

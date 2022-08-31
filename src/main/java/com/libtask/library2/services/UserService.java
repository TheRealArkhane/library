package com.libtask.library2.services;

import com.libtask.library2.entities.User;
import com.libtask.library2.dto.UserDto;
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

     private final UserRepository userRepository;

     public UserDto userToUserDto(User user) {
          return new UserDto(
                  user.getFirstName(),
                  user.getLastName(),
                  user.getEmail());
     }

     public List<User> showAllUsers() {
          return userRepository.showAllUsers();
     }

     public User getUserById(Long id) {
          return userRepository.findById(id).orElseThrow();
     }

     public User getUserByEmail(String email) {
          return userRepository.findByEmailIgnoreCase(email).orElseThrow();
     }

     public void deleteUser(User user) {
          userRepository.delete(user);
     }

     @Override
     public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
          return userRepository.findByEmailIgnoreCase(email).orElseThrow(()
                  -> new UsernameNotFoundException("User not found"));
     }
}

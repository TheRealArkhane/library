package com.libtask.library2.services;

import com.libtask.library2.entities.Book;
import com.libtask.library2.entities.User;
import com.libtask.library2.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
     private final UserRepository userRepository;

     public List<User> showAllUsers() {
          return userRepository.showAllUsers();
     }

     public User getUserById(String id) {
          return userRepository.findById(id).get();
     }

     public void changeEmail(String email, User user) {
          userRepository.changeEmail(email, user.getId());
     }

}

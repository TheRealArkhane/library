package com.libtask.library2.controllers;

import com.libtask.library2.dto.UserDto;
import com.libtask.library2.entities.User;
import com.libtask.library2.exceptions.BalanceConditionException;
import com.libtask.library2.repositories.BookRepository;
import com.libtask.library2.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @GetMapping("/all")
    public Page<User> showAllUsers(@PageableDefault(sort = "id") Pageable page) {
        return userRepository.findAll(page);
    }

    @GetMapping("/{id}")
    public UserDto showUserInformation(@PathVariable(value = "id") Long id) throws IllegalArgumentException {
        return new UserDto(
                userRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("User with this ID is not exist")));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable(value = "id") Long id)
            throws IllegalArgumentException, BalanceConditionException {
        if (bookRepository.findTakenBooksByUserId(id, Pageable.unpaged()).isEmpty()) {
            userRepository.delete(
                    userRepository.findById(id)
                            .orElseThrow(() -> new IllegalArgumentException("User with this ID is not exist")));
        }
        else throw new BalanceConditionException("Can't delete user with positive balance");
    }

    @GetMapping("/current")
    public User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}


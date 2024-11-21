package com.example.task_management_system.service.impl;

import com.example.task_management_system.model.User;
import com.example.task_management_system.repository.UserRepository;
import com.example.task_management_system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    /**
     * find user by email and intern it into author name for comment and task
     * @param email of user
     * @return String author name wich containig a first name and last name of finding user
     * @throws IllegalArgumentException if user is not a member of the repository
     */
    @Override
    public String getAuthorNameByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found with email: " + email));
        String authorName = user.getFirstname() + " " + user.getLastname();

        return authorName;
    }
}

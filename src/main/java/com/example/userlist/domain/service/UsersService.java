package com.example.userlist.domain.service;

import com.example.userlist.domain.entity.User;

import java.util.List;

public interface UsersService {
    User getUserById(int id);

    List<User> getAllUsers();

    void createUser(User user) throws Exception;

    void updateUser(User user) throws Exception;

    void deleteUser(User user) throws Exception;
}
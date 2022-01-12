package com.example.userlist.domain.repository;

import com.example.userlist.domain.entity.User;

import java.util.List;

public interface UserRepository {

    List<User> getAll();

    User getUserById(int id);

    boolean createUser(User user);

    boolean updateUser(User user);

    boolean deleteUser(User user);
}
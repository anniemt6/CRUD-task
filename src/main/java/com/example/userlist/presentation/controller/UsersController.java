package com.example.userlist.presentation.controller;

import com.example.userlist.data.dao.UserDao;
import com.example.userlist.domain.entity.User;
import com.example.userlist.presentation.entity.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Consumer;

@RestController
@RequestMapping("/users")
public class UsersController {
    private final UserDao userDao;

    private static final int STATUS_CODE_SUCCESS = 200;
    private static final int STATUS_CODE_ERROR = 500;

    @Autowired
    public UsersController(UserDao userDao) {
        this.userDao = userDao;
    }

    private Response<Void> handleRequest(User user, Consumer<User> userConsumer) {
        try {
            userConsumer.accept(user);
            return new Response(user, STATUS_CODE_SUCCESS, null);
        } catch (Exception e) {
            return new Response(user, STATUS_CODE_ERROR, e.getMessage());
        }
    }

    @GetMapping("/getAll")
    public Response<List<User>> getAllUsers() {
        List<User> users = userDao.getAll();
        return new Response<>(users, STATUS_CODE_SUCCESS, null);
    }

    @GetMapping("/getById")
    public Response<User> getById(@RequestParam("userId") int userId) {
        User user = userDao.getById(userId);
        return new Response<>(user, STATUS_CODE_SUCCESS, null);
    }

    @PostMapping("/create")
    public Response<Void> createUser(@RequestBody User user) {
        return handleRequest(user, userDao::create);
    }

    @PutMapping("/update")
    public Response<Void> updateUser(@RequestBody User user) {
        return handleRequest(user, userDao::update);
    }

    @DeleteMapping("/delete")
    public Response<Void> deleteUser(@RequestBody User user) {
        return handleRequest(user, userDao::delete);
    }
}
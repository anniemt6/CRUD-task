package com.example.userlist.presentation.controller;

import com.example.userlist.domain.entity.User;
import com.example.userlist.domain.service.UsersService;
import com.example.userlist.presentation.entity.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {
    private final UsersService usersService;

    private static final int STATUS_CODE_SUCCESS = 200;
    private static final int STATUS_CODE_ERROR = 500;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @FunctionalInterface
    public interface ResponseHandler {
        void accept(User user) throws Exception;
    }

    private Response<Void> handleRequest(User user, ResponseHandler userResponse) {
        try {
            userResponse.accept(user);
            return new Response(user, STATUS_CODE_SUCCESS, null);
        } catch (Exception e) {
            return new Response(user, STATUS_CODE_ERROR, e.getMessage());
        }
    }

    @GetMapping("/getAll")
    public Response<List<User>> getAllUsers() {
        List<User> users = usersService.getAllUsers();
        return new Response<>(users, STATUS_CODE_SUCCESS, null);
    }

    @GetMapping("/getById")
    public Response<User> getById(@RequestParam("userId") int userId) {
        User user = usersService.getUserById(userId);
        return new Response<>(user, STATUS_CODE_SUCCESS, null);
    }

    @PostMapping("/create")
    public Response<Void> createUsers(@RequestBody User user) {
        return handleRequest(user, usersService::createUser);
    }

    @PutMapping("/update")
    public Response<Void> updateUsers(@RequestBody User user) {
        return handleRequest(user, usersService::updateUser);
    }

    @DeleteMapping("/delete")
    public Response<Void> deleteUsers(@RequestBody User user) {
        return handleRequest(user, usersService::deleteUser);
    }
}
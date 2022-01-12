package com.example.userlist.domain.interactor;

import com.example.userlist.domain.entity.User;
import com.example.userlist.domain.exception.UserDataInvalidException;
import com.example.userlist.domain.exception.UserOperationException;

import java.util.List;

public interface UsersInteractor {

    User getUserBy(int id);

    List<User> getAllUsers();

    void createUser(User user) throws UserDataInvalidException, UserOperationException;

    void updateUser(User user) throws UserOperationException;

    void deleteUser(User user) throws UserOperationException;
}

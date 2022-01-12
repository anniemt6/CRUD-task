package com.example.userlist.domain.interactor.impl;

import com.example.userlist.domain.entity.User;
import com.example.userlist.domain.exception.UserDataInvalidException;
import com.example.userlist.domain.exception.UserOperationException;
import com.example.userlist.domain.interactor.UsersInteractor;
import com.example.userlist.domain.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsersInteractorImpl implements UsersInteractor {

    private final UsersRepository repository;

    @Autowired
    public UsersInteractorImpl(UsersRepository repository) {
        this.repository = repository;
    }

    @Override
    public User getUserBy(int id) {
        return repository.getUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return repository.getAll();
    }

    @Override
    public void createUser(User user) throws UserDataInvalidException, UserOperationException {
        if (isValid(user)) {
            if (!repository.createUser(user)) {
                throw new UserOperationException("Error has been acquired while creating user");
            }
        } else {
            throw new UserDataInvalidException("User data invalid");
        }
    }

    private boolean isValid(User user) {
        return user.getUserAge() > 0 && !user.getUserName().isEmpty() && !user.getUserSurname().isEmpty();
    }

    @Override
    public void updateUser(User user) throws UserOperationException {
        if (!repository.updateUser(user)) {
            throw new UserOperationException("Error has been acquired while updating user");
        }
    }

    @Override
    public void deleteUser(User user) throws UserOperationException {
        if (!repository.deleteUser(user)) {
            throw new UserOperationException("Error has been acquired while deleting user");
        }
    }
}

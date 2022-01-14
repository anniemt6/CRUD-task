package com.example.userlist.domain.service.impl;

import com.example.userlist.data.dao.UserDao;
import com.example.userlist.domain.entity.User;
import com.example.userlist.domain.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsersServiceImpl implements UsersService {
    private final UserDao userDao;

    @Autowired
    public UsersServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getUserById(int id) {
        return userDao.getById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAll();
    }

    @Override
    public void createUser(User user) throws Exception {
        if (isValidUserData(user)) {
            if (!userDao.create(user)) {
                throw new Exception("Error has been acquired while creating user");
            }
        } else {
            throw new Exception("User data invalid");
        }
    }

    private boolean isValidUserData(User user) {
        return user.getUserAge() > 0 && !user.getUserName().isEmpty() && !user.getUserSurname().isEmpty();
    }

    @Override
    public void updateUser(User user) throws Exception {
        if (!userDao.update(user)) {
            throw new Exception("Error has been acquired while updating user");
        }
    }

    @Override
    public void deleteUser(User user) throws Exception {
        if (!userDao.delete(user)) {
            throw new Exception("Error has been acquired while deleting user");
        }
    }
}
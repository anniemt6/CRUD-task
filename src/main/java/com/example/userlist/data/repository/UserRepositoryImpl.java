package com.example.userlist.data.repository;

import com.example.userlist.data.dao.UserDao;
import com.example.userlist.domain.repository.UserRepository;
import com.example.userlist.domain.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRepositoryImpl implements UserRepository {

    private final UserDao userDao;

    @Autowired
    public UserRepositoryImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public User getUserById(int id) {
        return userDao.getById(id);
    }

    @Override
    public boolean createUser(User user) {
        return userDao.create(user);
    }

    @Override
    public boolean updateUser(User user) {
        return userDao.update(user);
    }

    @Override
    public boolean deleteUser(User user) {
        return userDao.delete(user);
    }
}
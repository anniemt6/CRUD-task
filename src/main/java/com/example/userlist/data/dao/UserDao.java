package com.example.userlist.data.dao;

import com.example.userlist.domain.entity.User;

import java.util.List;

public interface UserDao extends BaseDao<User> {

    User getById(int id);

    List<User> getAll();
}

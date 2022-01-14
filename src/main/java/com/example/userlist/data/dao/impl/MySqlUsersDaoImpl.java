package com.example.userlist.data.dao.impl;

import com.example.userlist.data.dao.GenericDao;
import com.example.userlist.data.dao.UserDao;
import com.example.userlist.data.utils.DbConnector;
import com.example.userlist.domain.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@Component
public class MySqlUsersDaoImpl extends GenericDao implements UserDao {
    private static final String GET_BY_ID = "select * from user where user_id = ?";
    private static final String GET_ALL = "select * from user";
    private static final String CREATE = "insert into user values (?, ?, ?, ?)";
    private static final String UPDATE = "update user set user_name = ?, user_surname = ?, user_age = ? where user_id = ?";
    private static final String DELETE = "delete from user where user_id = ?";

    private static final int ID_COLUMN_INDEX = 1;
    private static final int NAME_COLUMN_INDEX = 2;
    private static final int SURNAME_COLUMN_INDEX = 3;
    private static final int AGE_COLUMN_INDEX = 4;

    private final Mapper<User> userMapper = resultSet -> {
        if (resultSet.next()) {
            return new User(
                    resultSet.getInt(ID_COLUMN_INDEX),
                    resultSet.getString(NAME_COLUMN_INDEX),
                    resultSet.getString(SURNAME_COLUMN_INDEX),
                    resultSet.getInt(AGE_COLUMN_INDEX)
            );
        } else {
            return null;
        }
    };

    private final Mapper<List<User>> userListMapper = resultSet -> {
        List<User> users = new LinkedList<>();
        User user = userMapper.map(resultSet);
        while (user != null) {
            users.add(user);
            user = userMapper.map(resultSet);
        }
        return users;
    };

    @Autowired
    public MySqlUsersDaoImpl(DbConnector dbConnector) { super(dbConnector); }

    @Override
    public User getById(int id) {
        User user = null;
        try {
            user = executeQuery(GET_BY_ID, userMapper, statement -> {
                statement.setInt(ID_COLUMN_INDEX, id);
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> result = null;
        try  {
            result = executeQuery(GET_ALL, userListMapper, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean create(User user) {
        try {
            executeQuery(CREATE, null, statement -> {
                setUserDataToStatement(user, statement);
            });
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(User user) {
        try {
            executeQuery(UPDATE, null, statement -> {
                setUserDataToStatement(user, statement);
            });
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void setUserDataToStatement(User user, PreparedStatement statement) throws SQLException {
        statement.setInt(ID_COLUMN_INDEX, user.getId());
        statement.setString(NAME_COLUMN_INDEX, user.getUserName());
        statement.setString(SURNAME_COLUMN_INDEX, user.getUserSurname());
        statement.setInt(AGE_COLUMN_INDEX, user.getUserAge());
    }

    @Override
    public boolean delete(User user) {
        try {
            executeQuery(DELETE, null, statement -> {
                statement.setInt(ID_COLUMN_INDEX, user.getId());
            });
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
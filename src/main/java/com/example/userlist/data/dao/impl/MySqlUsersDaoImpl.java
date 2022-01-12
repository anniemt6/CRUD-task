package com.example.userlist.data.dao.impl;

import com.example.userlist.data.dao.UserDao;
import com.example.userlist.data.utils.DbConnector;
import com.example.userlist.data.utils.DbService;
import com.example.userlist.data.dao.mappers.DbMapper;
import com.example.userlist.domain.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@Component
public class MySqlUsersDaoImpl implements UserDao {

    private final DbConnector dbConnector;

    private static final String GET_BY_ID = "select * from user where user_id = ?";
    private static final String GET_ALL = "select * from user";
    private static final String CREATE = "insert into user values (?, ?, ?, ?)";
    private static final String UPDATE = "update user set user_name = ?, user_surname = ?, user_age = ? where user_id = ?";
    private static final String DELETE = "delete from user where user_id = ?";

    @Autowired
    public MySqlUsersDaoImpl(DbConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    @Override
    public User getById(int id) {

        User user = null;

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {

            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(GET_BY_ID);
            statement.setInt(1, id);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = DbMapper.toUser(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbService.closeResultSet(resultSet);
            DbService.closeStatement(statement);
            DbService.closeConnection(connection);
        }

        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> result = new LinkedList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try  {

            connection = dbConnector.getConnection();
            statement = connection.createStatement();

            resultSet = statement.executeQuery(GET_ALL);

            while (resultSet.next()) {
                result.add(DbMapper.toUser(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbService.closeResultSet(resultSet);
            DbService.closeStatement(statement);
            DbService.closeConnection(connection);
        }

        return result;
    }

    @Override
    public boolean create(User user) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection  = dbConnector.getConnection();
            statement = connection.prepareStatement(CREATE);

            statement.setInt(1, user.getId());
            statement.setString(2, user.getUserName());
            statement.setString(3, user.getUserSurname());
            statement.setInt(4, user.getUserAge());

            return statement.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbService.closeStatement(statement);
            DbService.closeConnection(connection);
        }
        return false;
    }

    @Override
    public boolean update(User user) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(UPDATE);

            statement.setInt(1, user.getId());
            statement.setString(2, user.getUserName());
            statement.setString(3, user.getUserSurname());
            statement.setInt(4, user.getUserAge());

            return statement.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbService.closeStatement(statement);
            DbService.closeConnection(connection);
        }

        return false;
    }

    @Override
    public boolean delete(User user) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(DELETE);
            statement.setInt(1, user.getId());

            return statement.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbService.closeStatement(statement);
            DbService.closeConnection(connection);
        }
        return false;
    }
}
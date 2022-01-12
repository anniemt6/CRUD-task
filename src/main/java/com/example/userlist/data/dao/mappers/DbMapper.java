package com.example.userlist.data.dao.mappers;

import com.example.userlist.domain.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DbMapper {

    public static User toUser(ResultSet result) throws SQLException {
        return new User(
                result.getInt(1),
                result.getString(2),
                result.getString(3),
                result.getInt(4)
        );
    }
}

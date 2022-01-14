package com.example.userlist.data.dao;

import com.example.userlist.data.utils.DbConnector;
import org.springframework.lang.Nullable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.example.userlist.data.utils.DbService.*;

public abstract class GenericDao {
    private final DbConnector dbConnector;

    public GenericDao(DbConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    protected <T> T executeQuery(
            String sqlQuery,
            @Nullable Mapper<T> mapper,
            @Nullable StatementConfigurer statementConfigurer
    ) throws SQLException {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = dbConnector.getConnection();
            statement = connection.prepareStatement(sqlQuery);
            if (statementConfigurer != null) {
                statementConfigurer.setupStatement(statement);
            }

            if (mapper != null) {
                resultSet = statement.executeQuery();
                return mapper.map(resultSet);
            } else {
                int updatedRows = statement.executeUpdate();
                if (updatedRows == 0) {
                    throw new SQLException("Update operation failed, updated rows: " + updatedRows);
                }
            }
            return null;
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
            closeConnection(connection);
        }
    }

    protected interface Mapper<T> {
        T map(ResultSet resultSet) throws SQLException;
    }

    protected interface StatementConfigurer {
        void setupStatement(PreparedStatement statement) throws SQLException;
    }
}
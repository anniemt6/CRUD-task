package com.example.userlist.data.utils.queries;

public class UserQueries {

    public static final String GET_ALL = "select * from user";

    public static final String CREATE = "insert into user values (?)";

    public static final String UPDATE = "update user set user_name = ?, user_surname = ?, user_age = ? where user_id = ?";

    public static final String DELETE = "delete from user where user_id = ?";
}

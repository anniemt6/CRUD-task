package com.example.userlist.data.dao;

public interface BaseDao<T> {
    boolean create(T obj);

    boolean update(T obj);

    boolean delete(T obj);
}
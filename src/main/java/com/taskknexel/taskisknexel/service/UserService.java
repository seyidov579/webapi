package com.taskknexel.taskisknexel.service;

import com.taskknexel.taskisknexel.model.Users;

import java.util.List;

public interface UserService {
    List<Users> findAll();
    Users create(Users users);
    Users findById(Long id);
    Users edit(Users users);
    void deleteById(Long id);
    void deleteAll();
}

package com.taskknexel.taskisknexel.service;

import com.taskknexel.taskisknexel.model.Users;
import com.taskknexel.taskisknexel.repositories.UsersRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class UserServiceImpl implements UserService{

    @Autowired
    private UsersRepositories usersRepositories;

    @Override
    public List<Users> findAll() {
        return this.usersRepositories.findAll();
    }

    @Override
    public Users create(Users users) {
        return this.usersRepositories.save(users);
    }

    @Override
    public Users findById(Long id) {
        return this.usersRepositories.findOne(id);
    }

    @Override
    public Users edit(Users users) {
        return this.usersRepositories.save(users);
    }

    @Override
    public void deleteById(Long id) {
        this.usersRepositories.delete(id);
    }

    @Override
    public void deleteAll() {
        this.usersRepositories.deleteAll();
    }
}

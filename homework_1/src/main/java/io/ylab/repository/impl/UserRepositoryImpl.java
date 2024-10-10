package io.ylab.repository.impl;

import io.ylab.model.User;
import io.ylab.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    public static UserRepositoryImpl instance;
    private final List<User> users;

    public UserRepositoryImpl() {
        users = new ArrayList<>();
    }

    public UserRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new UserRepositoryImpl();
        }
        return instance;
    }

    @Override
    public User save(User user) {
        users.add(user);
        return user;
    }

    @Override
    public User findById(Integer id) {
        return users.get(id);
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public boolean deleteById(Integer id) {
        return users.removeIf(user -> user.getId() == id);
    }
}

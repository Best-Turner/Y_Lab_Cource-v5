package io.ylab.repository.impl;

import io.ylab.model.User;
import io.ylab.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private static UserRepositoryImpl instance;
    private final List<User> users;

    private UserRepositoryImpl() {
        users = new ArrayList<>();
        addDefaultUser();
    }

    public static UserRepositoryImpl getInstance() {
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
        return users.stream().filter(user -> user.getId() == id).findFirst().get();
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public boolean deleteById(Integer id) {
        return users.removeIf(user -> user.getId() == id);
    }

    private void addDefaultUser() {
        User admin = new User("a", "a", "a", User.Role.ADMIN);
        admin.setId(1);
        User user = new User("u", "u", "u", User.Role.USER);
        user.setId(2);
        users.add(admin);
        users.add(user);
    }
}

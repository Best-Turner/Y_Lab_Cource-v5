package io.ylab.service;

import io.ylab.exception.UserNotFoundException;
import io.ylab.model.Habit;
import io.ylab.model.User;

import java.util.Iterator;
import java.util.Optional;

public interface UserService {
    User save(User user);

    Optional<User> findById(int id);

    boolean deleteById(int id);

    Iterator<User> findAll();

    User update(int id, User user);

    Iterator<Habit> getHabits(int userId);

    boolean changeRole(int userid, User.Role role) throws UserNotFoundException;
}

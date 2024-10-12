package io.ylab.service;

import io.ylab.exception.UserNotFoundException;
import io.ylab.exeption.DuplicateEmailException;
import io.ylab.exeption.DuplicatePasswordException;
import io.ylab.model.Habit;
import io.ylab.model.User;

import java.util.Iterator;
import java.util.Optional;

public interface UserService {
    User save(User user) throws DuplicateEmailException, DuplicatePasswordException;

    Optional<User> findById(int id);
    Optional<User> findByEmail(String email);

    boolean deleteById(int id);

    Iterator<User> findAll();

    User update(int id, User user) throws DuplicatePasswordException, DuplicateEmailException;

//    Iterator<Habit> getHabits(int userId);

    Iterator<Habit> getHabits(int userId);

    boolean changeRole(int userid, User.Role role) throws UserNotFoundException;
}

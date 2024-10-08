package io.ylab.service.impl;

import io.ylab.exception.UserNotFoundException;
import io.ylab.model.Habit;
import io.ylab.model.User;
import io.ylab.repository.HabitRepository;
import io.ylab.repository.UserRepository;
import io.ylab.service.UserService;

import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final HabitRepository habitRepository;

    public UserServiceImpl(UserRepository repository, HabitRepository habitRepository) {
        this.repository = repository;
        this.habitRepository = habitRepository;
    }


    @Override
    public User save(User user) {
        int userId = getRandomId(user.getEmail());
        user.setId(userId);
        if (Objects.isNull(user.getName())) {
            user.setName("<user - " + userId + ">");
        }
        user.setRole(User.Role.USER);
        return repository.save(user);
    }

    @Override
    public Optional<User> findById(int id) {
        return Optional.of(repository.findById(id));
    }

    @Override
    public boolean deleteById(int id) {
        return repository.deleteById(id);
    }

    @Override
    public Iterator<User> findAll() {
        return repository.findAll().iterator();
    }

    @Override
    public User update(int id, User user) {
        User fromDb = repository.findById(id);
        fromDb.setName(user.getName());
        fromDb.setEmail(user.getEmail());
        fromDb.setPassword(user.getPassword());
        return fromDb;
    }

    @Override
    public Iterator<Habit> getHabits(int userId) {
        return habitRepository.findAll()
                .stream()
                .filter(habit -> habit.getIdOwner() == userId)
                .collect(Collectors.toList()).iterator();
    }

    @Override
    public boolean changeRole(int userid, User.Role role) throws UserNotFoundException {
        User user = existUser(userid);
        user.setRole(role);
        return true;
    }

    private int getRandomId(String line) {
        return Integer.valueOf(UUID.fromString(line).toString().substring(0, 5));
    }

    public User existUser(int id) throws UserNotFoundException {
        return Optional.of(repository.findById(id)).orElseThrow(()
                -> new UserNotFoundException("Пользователь с id = " + id + " не найден"));
    }
}

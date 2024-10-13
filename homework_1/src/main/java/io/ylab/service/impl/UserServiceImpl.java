package io.ylab.service.impl;

import io.ylab.exception.*;
import io.ylab.model.Habit;
import io.ylab.model.User;
import io.ylab.repository.UserRepository;
import io.ylab.service.HabitService;
import io.ylab.service.UserService;

import java.util.*;

public class UserServiceImpl implements UserService {

    private static Set<String> cacheUserEmails;
    private static Set<String> cacheUserPassword;
    private final UserRepository repository;
    private final HabitService habitService;


    public UserServiceImpl(UserRepository repository, HabitService habitService) {
        this.repository = repository;
        this.habitService = habitService;
        cacheUserEmails = new HashSet<>();
        cacheUserPassword = new HashSet<>();
    }


    @Override
    public User save(User user) throws DuplicateEmailException, DuplicatePasswordException {
        checkUserData(user.getEmail(), user.getPassword());
        int userId = getRandomId();
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
    public Optional<User> findByEmail(String email) {
        return repository
                .findAll()
                .stream()
                .filter(user -> user.getEmail()
                        .equals(email))
                .findFirst();
    }

    @Override
    public boolean deleteById(int id) {
        while (getHabits(id).hasNext()) {
            habitService.deleteById(getHabits(id).next().getId());
        }
        User userBeDeleted = repository.findById(id);
        cacheUserPassword.remove(userBeDeleted.getPassword());
        cacheUserEmails.remove(userBeDeleted.getEmail());
        return repository.deleteById(id);
    }

    @Override
    public Iterator<User> findAll() {
        return repository.findAll().iterator();
    }

    @Override
    public User update(int id, User user) throws DuplicatePasswordException, DuplicateEmailException {
        User fromDb = repository.findById(id);
        String oldPassword = fromDb.getPassword();
        String oldEmail = fromDb.getEmail();
        String newPassword = user.getPassword();
        String newEmail = user.getEmail();
        if (!oldEmail.equals(newEmail)) {
            checkIsUniqueEmail(newEmail);
            cacheUserEmails.remove(oldEmail);
            cacheUserEmails.add(newEmail);
        }
        if (!oldPassword.equals(newPassword)) {
            checkIsUniquePassword(newPassword);
            cacheUserPassword.remove(oldPassword);
            cacheUserPassword.add(newPassword);
        }
        fromDb.setName(user.getName());
        fromDb.setEmail(newEmail);
        fromDb.setPassword(newPassword);
        return fromDb;
    }

    @Override
    public void deleteHabitById(int userId, int habitId) throws UserNotFoundException, HabitNotFoundException, AccessException {
        Habit habitById = habitService.getHabitById(habitId)
                .orElseThrow(() -> new HabitNotFoundException("Привычка не найдена"));
        User userById = findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден"));

        if (userById.getRole().equals(User.Role.ADMIN)) {
            habitService.deleteById(habitId);
        } else {
            if (habitById.getIdOwner() == userId) {
                habitService.deleteById(habitId);
            } else {
                throw new AccessException("У вас нет привычки с ID = " + habitById);
            }
        }
    }

    @Override
    public Iterator<Habit> getHabits(int userId) {
        return habitService.getHabitsByUserId(userId);
    }

    @Override
    public boolean changeRole(int userid, User.Role role) throws UserNotFoundException {
        User user = existUser(userid);
        user.setRole(role);
        return true;
    }

    private int getRandomId() {
        Random random = new Random();
        return random.nextInt(100);
    }

    public User existUser(int id) throws UserNotFoundException {
        return Optional.of(repository.findById(id)).orElseThrow(()
                -> new UserNotFoundException("Пользователь с id = " + id + " не найден"));
    }

    private void checkIsUniqueEmail(String email) throws DuplicateEmailException {
        if (!cacheUserEmails.add(email)) {
            throw new DuplicateEmailException("Данный email уже зарегистрирован");
        }
    }


    private void checkUserData(String email, String password) throws DuplicatePasswordException, DuplicateEmailException {
        checkIsUniqueEmail(email);
        checkIsUniquePassword(password);
    }

    private void checkIsUniquePassword(String password) throws DuplicatePasswordException {
        if (!cacheUserPassword.add(password)) {
            throw new DuplicatePasswordException("Придумайте другой пароль");
        }
    }
}
package io.ylab.service.impl;

import io.ylab.exception.UserNotFoundException;
import io.ylab.exeption.DuplicateEmailException;
import io.ylab.exeption.DuplicatePasswordException;
import io.ylab.model.User;
import io.ylab.repository.UserRepository;
import io.ylab.service.UserService;

import java.util.*;

public class UserServiceImpl implements UserService {

    private static Set<String> cacheUserEmails;
    private static Set<String> cacheUserPassword;
    private final UserRepository repository;
    //private final HabitRepository habitRepository;


    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
        //this.habitRepository = habitRepository;
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
    public boolean deleteById(int id) {

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

//    @Override
//    public Iterator<Habit> getHabits(int userId) {
//        return habitRepository.findAll()
//                .stream()
//                .filter(habit -> habit.getIdOwner() == userId)
//                .collect(Collectors.toList()).iterator();
//    }

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
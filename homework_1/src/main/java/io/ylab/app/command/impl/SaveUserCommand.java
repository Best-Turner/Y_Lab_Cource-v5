package io.ylab.app.command.impl;

import io.ylab.app.command.Command;
import io.ylab.exeption.DuplicateEmailException;
import io.ylab.exeption.DuplicatePasswordException;
import io.ylab.model.User;
import io.ylab.service.UserService;

import java.util.Scanner;

public class SaveUserCommand implements Command {
    private Scanner scanner;

    private final UserService service;

    public SaveUserCommand(UserService service) {
        scanner = new Scanner(System.in);
        this.service = service;
    }


    @Override
    public void execute() {
        User user = new User();
        user.setName(getsUserName());
        user.setEmail(getsUserEmail());
        user.setPassword(getsUserPassword());
        try {
            User save = service.save(user);
            System.out.println("Пользователь " + save.getName() + " сохранен");
        } catch (DuplicateEmailException | DuplicatePasswordException e) {
            System.out.println(e.getMessage());
        }
    }

    private String getsUserName() {
        System.out.println("Введите имя пользователя:");
        return scanner.nextLine();
    }

    private String getsUserEmail() {
        System.out.println("Введите Email:");
        return scanner.nextLine();
    }

    private String getsUserPassword() {
        System.out.println("Введите пароль:");
        return scanner.nextLine();
    }
}

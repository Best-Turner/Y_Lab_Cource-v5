package io.ylab.app.command.impl.userCommand;

import io.ylab.app.command.Command;
import io.ylab.exception.UserNotFoundException;
import io.ylab.exception.DuplicateEmailException;
import io.ylab.exception.DuplicatePasswordException;
import io.ylab.model.User;
import io.ylab.service.AutenticationService;
import io.ylab.service.UserService;

import java.util.Optional;
import java.util.Scanner;

public class UpdateProfile implements Command {
    private final UserService userService;
    private final AutenticationService autenticationService;
    private Scanner scanner;

    public UpdateProfile(UserService userService, AutenticationService autenticationService) {
        this.userService = userService;
        this.autenticationService = autenticationService;
        scanner = new Scanner(System.in);
    }

    @Override
    public void execute() {
        Optional<User> userById = userService.findById(autenticationService.getCurrentUser().getId());
        if (userById.isPresent()) {
            User updatedUser = new User();
            updatedUser.setName(getInputName());
            updatedUser.setEmail(getInputEmail());
            updatedUser.setPassword(getInputPassword());
            try {
                userService.update(userById.get().getId(), updatedUser);
            } catch (DuplicatePasswordException | DuplicateEmailException e) {
                System.out.println(e.getMessage());
            }
        } else {
            try {
                userById.orElseThrow(() -> new UserNotFoundException("Пользователь не существует"));
            } catch (UserNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    private String getInputName() {
        System.out.println("Введите новое имя пользователя: ");
        return scanner.nextLine();
    }

    private String getInputEmail() {
        System.out.println("Введите новый email: ");
        return scanner.nextLine();
    }

    private String getInputPassword() {
        System.out.println("Введите новый пароль пользователя: ");
        return scanner.nextLine();
    }
}

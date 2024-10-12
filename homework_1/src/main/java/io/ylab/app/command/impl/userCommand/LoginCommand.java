package io.ylab.app.command.impl.userCommand;

import io.ylab.app.command.Command;
import io.ylab.service.AutenticationService;

import java.util.Scanner;

public class LoginCommand implements Command {
    private final AutenticationService authentication;
    private static String USER_NOT_REGISTER = """
            -------------------------------\nПользователь не зарегистрирован\n-------------------------------
            """;

    private Scanner scanner;

    public LoginCommand(AutenticationService authentication) {
        this.authentication = authentication;
        scanner = new Scanner(System.in);

    }

    @Override
    public void execute() {
        boolean authenticate = authentication.authenticate(getInputEmail(), getInputPassword());
        if (!authenticate) {
            System.out.println(USER_NOT_REGISTER);
        } else {
            System.out.println("Добро пожаловать, " + authentication.getCurrentUser().getName());
        }
    }


    private String getInputEmail() {
        System.out.println("Введите email пользователя:");
        return scanner.nextLine();
    }

    private String getInputPassword() {
        System.out.println("Введите пароль пользователя:");
        return scanner.nextLine();
    }
}

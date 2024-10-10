package io.ylab;

import io.ylab.app.HabitManagerApp;
import io.ylab.app.command.Command;
import io.ylab.app.command.impl.SaveUserCommand;
import io.ylab.app.menu.MenuComponent;
import io.ylab.app.menu.impl.CommandMenu;
import io.ylab.app.menu.impl.CompositeMenu;
import io.ylab.repository.UserRepository;
import io.ylab.repository.impl.UserRepositoryImpl;
import io.ylab.service.UserService;
import io.ylab.service.impl.UserServiceImpl;

public class Main {
    public static void main(String[] args) {

        UserRepository userRepository = new UserRepositoryImpl();
        UserService userService = new UserServiceImpl(userRepository);
        Command registerCommand = new SaveUserCommand(userService);

        MenuComponent mainMenu = new CompositeMenu("Главное меню");

        MenuComponent registrationMenu = new CommandMenu("Регистрация пользователя");
        registrationMenu.setParent(mainMenu);
        MenuComponent singInMenu = new CommandMenu("Войти");
        singInMenu.setParent(mainMenu);

        registrationMenu.setCommand(registerCommand);

        mainMenu.addMenu(registrationMenu);
        mainMenu.addMenu(singInMenu);

        HabitManagerApp app = new HabitManagerApp(mainMenu, registerCommand);
        app.run(mainMenu);

    }
}
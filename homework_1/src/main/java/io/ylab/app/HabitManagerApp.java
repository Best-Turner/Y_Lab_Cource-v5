package io.ylab.app;

import io.ylab.app.command.impl.ProgramCloseCommand;
import io.ylab.app.command.impl.habitCommand.AddHabitCommand;
import io.ylab.app.command.impl.habitCommand.ChangeStatusHabitCommand;
import io.ylab.app.command.impl.habitCommand.ShowAllMyHabitCommand;
import io.ylab.app.command.impl.habitCommand.UpdateHabitCommand;
import io.ylab.app.command.impl.userCommand.*;
import io.ylab.app.menu.MenuComponent;
import io.ylab.app.menu.impl.CommandMenu;
import io.ylab.app.menu.impl.CompositeMenu;
import io.ylab.exeption.CommandUnSupportedException;
import io.ylab.service.AutenticationService;
import io.ylab.service.HabitService;
import io.ylab.service.UserService;

import java.util.Scanner;
import java.util.Stack;

public class HabitManagerApp {
    private final AutenticationService authentication;
    private final MenuComponent mainMenu;
    private MenuComponent currentMenu;
    private Stack<MenuComponent> menuStack;
    private final UserService userService;
    private final HabitService habitService;
    private Scanner scanner;

    public HabitManagerApp(AutenticationService authentication, UserService userService, HabitService habitService) {
        this.authentication = authentication;
        this.userService = userService;
        this.habitService = habitService;
        menuStack = new Stack<>();
        mainMenu = new CompositeMenu("Главное меню");
        scanner = new Scanner(System.in);

    }

    public void run() {
        int command;
        while (true) {
            displayMenuDependingOnAuthentication();
            currentMenu.execute();
            shouldAddBackBatton();
            try {
                command = readNumericCommand();
            } catch (NumberFormatException e) {
                System.out.println("Вы введи не числовое значение. Повторите попытку");
                continue;
            }
            if (isBack(command)) {
                continue;
            }
            try {
                MenuComponent child = currentMenu.getChild(command);
                if (child instanceof CompositeMenu) {
                    menuStack.push(currentMenu);
                    currentMenu = child;
                } else {
                    child.execute();
                }
            } catch (CommandUnSupportedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void displayMenuDependingOnAuthentication() {
        if (!authentication.isAuthentication()) {
            currentMenu = menuForUserWithoutAuthentication();
            menuStack.clear();
        } else if (authentication.isAuthentication() && menuStack.empty()) {
            currentMenu = menuForUserWithAuthentication();
        }
    }

    private void shouldAddBackBatton() {
        if (!menuStack.empty()) {
            System.out.println(0 + " - " + "Назад");
        }
    }

    private int readNumericCommand() throws NumberFormatException {
        System.out.println("\nВВЕДИТЕ КОМАНДУ:\n");

        int command = -1;
        String inputCommand = scanner.nextLine();
        command = Integer.parseInt(inputCommand);
        return command;
    }

    private boolean isBack(int command) {
        if (command == 0 && !menuStack.empty()) {
            currentMenu = menuStack.pop();
            return true;
        }
        return false;
    }

    private MenuComponent menuForUserWithAuthentication() {
        System.out.println("\n\t\t\tПользователь " + authentication.getCurrentUser().getName());
        mainMenu.clean();

        MenuComponent menuProfile = new CompositeMenu("Профиль");
        menuProfile.addMenu(new CommandMenu("Показать данные профиля", new ShowUserProfileCommand(authentication, userService)));
        menuProfile.addMenu(new CommandMenu("Редактировать профиль", new UpdateProfile(userService, authentication)));
        menuProfile.addMenu(new CommandMenu("Удалить профиль", new DeleteProfileCommand(authentication, userService)));

        MenuComponent menuHabit = new CompositeMenu("Меню привычек");
        menuHabit.addMenu(new CommandMenu("Показать все привычки", new ShowAllMyHabitCommand(authentication, userService)));
        menuHabit.addMenu(new CommandMenu("Создать новую привычку", new AddHabitCommand(authentication, habitService)));
        menuHabit.addMenu(new CommandMenu("Редактировать привычку", new UpdateHabitCommand(authentication, habitService)));
        menuHabit.addMenu(new CommandMenu("Изменить статус привычки", new ChangeStatusHabitCommand(authentication, habitService)));

        MenuComponent menuExit = new CommandMenu("Выйти из учетной записи", new LogOutCommand(authentication));

        mainMenu.addMenu(menuProfile);
        mainMenu.addMenu(menuHabit);
        mainMenu.addMenu(menuExit);
        return mainMenu;
    }

    private MenuComponent menuForUserWithoutAuthentication() {
        mainMenu.clean();
        mainMenu.addMenu(new CommandMenu("Регистрация нового пользователя", new SaveUserCommand(userService)));
        mainMenu.addMenu(new CommandMenu("Войти", new LoginCommand(authentication)));
        mainMenu.addMenu(new CommandMenu("Закрыть программу", new ProgramCloseCommand()));
        return mainMenu;
    }
}

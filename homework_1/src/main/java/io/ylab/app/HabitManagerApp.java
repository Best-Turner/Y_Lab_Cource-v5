package io.ylab.app;

import io.ylab.app.command.Command;
import io.ylab.app.menu.MenuComponent;
import io.ylab.exeption.CommandUnSupportedException;

import java.util.Scanner;

public class HabitManagerApp {

    private final MenuComponent menu;
    private final Command command;
    private Scanner scanner;

    public HabitManagerApp(MenuComponent menu, Command command) {
        this.menu = menu;
        this.command = command;
    }

//    public void run() {
//        int command;
//        scanner = new Scanner(System.in);
//        while (true) {
//            menu.execute();
//            System.out.println("Введите команду:");
//            command = scanner.nextInt();
//            if (command == -1) {
//                System.exit(0);
//            }
//            if (menu instanceof CommandMenu) {
//                menu.execute();
//            } else {
//                try {
//                    menu.getChild(command -1).execute();
//                } catch (CommandUnSupportedException e) {
//                    System.out.println(e.getMessage());
//                }
//            }
//        }
//    }

    public void run(MenuComponent menu) {
        scanner = new Scanner(System.in);
        menu.execute();
        System.out.println("Введите команду");
        int command = scanner.nextInt();
        try {
            if (command == menu.getCountLeaf() + 1) {
                if (menu.getParent() == null) {
                    System.out.println("Вы находитесь в главном меню");
                } else {
                    run(menu.getParent());
                }
            } else {
                run(menu.getChild(command - 1));
            }
        } catch (CommandUnSupportedException | UnsupportedOperationException e) {
            System.out.println(e.getMessage());
        }
    }
}

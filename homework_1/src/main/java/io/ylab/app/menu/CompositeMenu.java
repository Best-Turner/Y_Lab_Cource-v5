package io.ylab.app.menu;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CompositeMenu extends MenuComponent {
    private List<MenuComponent> menuList;
    private MenuComponent parent;
    private Scanner scanner;

    public CompositeMenu(String title) {
        this.title = title;
        menuList = new ArrayList<>();

    }

    @Override
    public void execute() {
        scanner = new Scanner(System.in);
        int command;

        while (true) {
            display();
            System.out.println("Введите команду");
            command = scanner.nextInt();
            if (command == -1) {
                System.exit(0);
            }
            if (command == menuList.size() + 1) {
                if (parent == null) {
                    System.out.println("Вы находитесь в главном меню");
                } else {
                    parent.execute();
                }
                continue;
            }
            menuList.get(command - 1).execute();
        }
    }

    @Override
    public void display() {
        int count = 1;
        for (MenuComponent menu : menuList) {
            System.out.println(count++ + " - " + menu.title);
        }
        System.out.println(count + " - " + "Назад");
    }

    @Override
    public void setParent(MenuComponent parent) {
        this.parent = parent;
    }

    @Override
    public void addMenu(MenuComponent menu) {
        menuList.add(menu);
    }
}

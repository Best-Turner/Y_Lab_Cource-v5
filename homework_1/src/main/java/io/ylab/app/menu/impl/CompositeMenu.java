package io.ylab.app.menu.impl;


import io.ylab.app.menu.MenuComponent;
import io.ylab.exeption.CommandUnSupportedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CompositeMenu extends MenuComponent {
    private List<MenuComponent> menuList;
    private String title;
    private MenuComponent parent;
    private Scanner scanner;

    private final static String WRONG_COMMAND = """
            -------------------------\nКоманда не поддерживается\n-------------------------
            """;


    public CompositeMenu(String title) {
        this.title = title;
        menuList = new ArrayList<>();

    }

    @Override
    public void execute() {
        display();
    }

    private void display() {
        int count = 1;
        for (MenuComponent menu : menuList) {
            System.out.println(count++ + " - " + menu.getTitle());
        }
        System.out.println(count + " - " + "Назад");
    }

    @Override
    public void setParent(MenuComponent parent) {
        this.parent = parent;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void addMenu(MenuComponent menu) {
        menuList.add(menu);
    }

    @Override
    public MenuComponent getChild(int index) throws CommandUnSupportedException {
        if (index < 0 || index >= menuList.size()) {
            throw new CommandUnSupportedException(WRONG_COMMAND);
        }
        return menuList.get(index);
    }

    @Override
    public int getCountLeaf() {
        return menuList.size();
    }

    @Override
    public MenuComponent getParent() {
        return parent;
    }
}

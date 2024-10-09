package io.ylab;

import io.ylab.app.menu.CommandMenu;
import io.ylab.app.menu.CompositeMenu;
import io.ylab.app.menu.MenuComponent;

public class Main {
    public static void main(String[] args) {

        MenuComponent mainMenu = new CompositeMenu("Гланое меню");

        MenuComponent composite1 = new CompositeMenu("Composite 1");
        MenuComponent composite2 = new CompositeMenu("Composite 2");

        composite1.addMenu(new CommandMenu("Command menu 1_1"));
        composite1.addMenu(new CommandMenu("Command menu 1_2"));
        composite1.setParent(mainMenu);
        composite2.setParent(mainMenu);

        mainMenu.addMenu(composite1);
        mainMenu.addMenu(composite2);

        mainMenu.execute();
    }
}
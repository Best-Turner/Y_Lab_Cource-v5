package io.ylab.app.menu.impl;

import io.ylab.app.command.Command;
import io.ylab.app.menu.MenuComponent;

public class CommandMenu extends MenuComponent {
    private final Command command;
    private String title;

    private MenuComponent parent;

    public CommandMenu(String title, Command command) {
        this.command = command;
        this.title = title;
    }

    @Override
    public void execute() {
        command.execute();
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
    public MenuComponent getParent() {
        return parent;
    }
}

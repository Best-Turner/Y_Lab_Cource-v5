package io.ylab.app.menu.impl;

import io.ylab.app.command.Command;
import io.ylab.app.menu.MenuComponent;

public class CommandMenu extends MenuComponent {
    private Command command;
    private String title;

    private MenuComponent parent;

    public CommandMenu(String title) {
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
    public void setCommand(Command command) {
        this.command = command;
    }

    @Override
    public MenuComponent getParent() {
        return parent;
    }
}

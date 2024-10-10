package io.ylab.app.menu;

import io.ylab.app.command.Command;
import io.ylab.exeption.CommandUnSupportedException;

public abstract class MenuComponent {

    private MenuComponent parent;

    public abstract void execute();

    public void addMenu(MenuComponent menu) {
        throw new UnsupportedOperationException();
    }
    public void setCommand(Command command) {
        throw new UnsupportedOperationException();
    }

    public int getCountLeaf() {
        throw new UnsupportedOperationException();
    }

    public MenuComponent getChild(int index) throws CommandUnSupportedException {
        throw new UnsupportedOperationException();
    }

    public abstract void setParent(MenuComponent parent);

    public abstract String getTitle();

    public MenuComponent getParent() {
        return parent;
    }
}

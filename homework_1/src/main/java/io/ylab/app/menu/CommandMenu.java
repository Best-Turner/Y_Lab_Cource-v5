package io.ylab.app.menu;

public class CommandMenu extends MenuComponent {

    private MenuComponent parent;

    public CommandMenu(String title) {
        this.title = title;
    }

    @Override
    public void execute() {
        System.out.println("Делаем что-то");
    }

    @Override
    public void setParent(MenuComponent parent) {
        this.parent = parent;
    }
}

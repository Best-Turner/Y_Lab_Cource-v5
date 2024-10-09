package io.ylab.app.menu;

public abstract class MenuComponent {
    protected String title;
    private MenuComponent parent;

    public abstract void execute();

    public void addMenu(MenuComponent menu) {
        throw new UnsupportedOperationException();
    }


    protected void display() {
        throw new UnsupportedOperationException();
    }

    public abstract void setParent(MenuComponent parent);
}

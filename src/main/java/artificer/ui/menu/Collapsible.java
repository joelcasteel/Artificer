package main.java.artificer.ui.menu;

import main.java.artificer.ui.menu.MenuWrapper.MenuTitle;

public interface Collapsible {
    public void collapse();
    public void expand();
    public void toggle();
    public MenuTitle getTitle();

}

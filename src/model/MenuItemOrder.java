package model;

import java.io.Serializable;

public class MenuItemOrder implements Serializable {
    private String menuItemID;
    private int numberOfItem;

    public MenuItemOrder(String menuItemID, int numberOfItem) {
        this.menuItemID = menuItemID;
        this.numberOfItem = numberOfItem;
    }

    public String getMenuItemID() {
        return menuItemID;
    }

    public int getNumberOfItem() {
        return numberOfItem;
    }

    @Override
    public String toString() {

        return "[menuItem=" + menuItemID + ", numberOfItem=" + numberOfItem + "]";

    }


}

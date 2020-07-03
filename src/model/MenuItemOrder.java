package model;

import java.io.Serializable;

public class MenuItemOrder implements Serializable {
    private MenuItem menuItem;
    private int numberOfItem;

    public MenuItemOrder(MenuItem menuItem,int numberOfItem){
        this.menuItem=menuItem;
        this.numberOfItem=numberOfItem;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public int getNumberOfItem() {
        return numberOfItem;
    }

    @Override
    public String toString() {

        return "[menuItem=" + menuItem + ", numberOfItem=" + numberOfItem + "]";

    }


}

package model;

import java.io.Serializable;
import java.util.Set;

public class Order implements Serializable {
    private int orderID;
    private int staffID;
    private Set<MenuItemOrder> menuItems;
    private double totalOrderPrice;
    private boolean isClosed;

    private static final long serialVersionUID = 1L;

    public Order(int orderID, int staffID, Set<MenuItemOrder> menuItems, double totalOrderPrice) {

    }

    public Order(int staffID, Set<MenuItemOrder> menuItems) {
        this.staffID = staffID;
        this.menuItems = menuItems;
        this.totalOrderPrice = getTotalPriceOrder();
        this.isClosed = false;

    }

    public boolean getClosed() {
        return this.isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }


    private double getTotalPriceOrder() {

        double totalPrice = 0;
        for (MenuItemOrder menuItemOrder : menuItems) {
            MenuItem menuItem = menuItemOrder.getMenuItem();
            double menuItemPrice = menuItem.getPrice();
            int numberOfItems = menuItemOrder.getNumberOfItem();

            totalPrice += menuItemPrice * numberOfItems;
        }

        return totalPrice;

    }

    public int getStaffID() {
        return staffID;
    }

    public void setTotalOrderPrice(double totalOrderPrice) {
        this.totalOrderPrice = totalOrderPrice;
    }

    public double getTotalOrderPrice() {
        return totalOrderPrice;
    }

    public Set<MenuItemOrder> getMenuItems() {
        return menuItems;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public void setMenuItems(Set<MenuItemOrder> menuItems) {
        this.menuItems = menuItems;
    }


    @Override
    public String toString() {
        String toPrint = "[orderID=" + orderID + ", staffID=" + staffID + ", ";

        toPrint += "items in order: ";
        for (MenuItemOrder menuItemOrder : menuItems) {
            toPrint += menuItemOrder.toString();
        }

        toPrint += "total price=" + totalOrderPrice + "]";

        return toPrint;

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + orderID;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        Order other = (Order) obj;
        if (orderID != other.orderID)
            return false;
        return true;
    }
}

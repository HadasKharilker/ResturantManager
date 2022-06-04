package model;

import java.io.Serializable;


public class MenuItem implements Serializable {

    private int itemID;
    private String itemName;
    private Double price;
    private MenuItemType itemType;

    private static final long serialVersionUID = 1L;

    public MenuItem(MenuItemBuilder menuItemBuilder) {
        super();
        this.itemID = menuItemBuilder.itemID;
        this.itemName = menuItemBuilder.itemName;
        this.price = menuItemBuilder.price;
        this.itemType = menuItemBuilder.itemType;
    }

    //setters and getters
    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setName(String itemName) {
        this.itemName = itemName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public MenuItemType getItemType() {
        return itemType;
    }

    public void setItemType(MenuItemType itemType) {
        this.itemType = itemType;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + itemID;
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
        MenuItem other = (MenuItem) obj;
        if (itemID != other.itemID)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "[id=" + itemID + ", name=" + itemName + ", price=" + price + ", itemType=" + itemType + "]";

    }

    public static class MenuItemBuilder {

        private int itemID;
        private String itemName;
        private Double price;
        private MenuItemType itemType;

        public MenuItemBuilder(int itemID) {
            this.itemID = itemID;
        }

        public MenuItemBuilder itemName(String itemName) {
            this.itemName = itemName;
            return this;
        }

        public MenuItemBuilder price(Double price) {
            this.price = price;
            return this;
        }

        public MenuItemBuilder itemType(MenuItemType itemType) {
            this.itemType = itemType;
            return this;
        }

        public MenuItem build() {
            MenuItem menuItem = new MenuItem(this);
            validateUserObject(menuItem);
            return menuItem;
        }

        private void validateUserObject(MenuItem menuItem) {
            //Do some basic validations to check
            //if user object does not break any assumption of system
        }
    }


}

package model.Service;


import model.MenuItem;
import model.MenuItemType;
import model.Repository.MenuItemRepositoryImpel;
import model.Repository.MenuRepository;

import java.util.Set;

public class MenuService {

    private final MenuRepository menuRepository;


    public MenuService() throws Exception {
        menuRepository = MenuItemRepositoryImpel.getInstance();
    }

    public boolean addNewItemToMenu(String menuID, String name, String price, String type) {
        try {
            MenuItem item = new MenuItem.MenuItemBuilder(Integer.parseInt(menuID)).itemName(name).price(Double.parseDouble(price)).itemType(MenuItemType.valueOf(type)).build();
            this.menuRepository.addMenuItem(item);
            return true;

        } catch (Exception e) {
            return false;
        }
    }


    public boolean editMenuItem(String menuID, String name, String price, String type) {
        try {
            MenuItem item = new MenuItem.MenuItemBuilder(Integer.parseInt(menuID)).itemName(name).price(Double.parseDouble(price)).itemType(MenuItemType.valueOf(type)).build();
            this.menuRepository.updateMenuItem(item);
            return true;


        } catch (Exception e) {
            return false;
        }

    }


    public boolean deleteMenuItem(String item) {
        try {
            this.menuRepository.deleteMenuItem(Integer.parseInt(item));
            return true;

        } catch (Exception e) {

            return false;
        }


    }

    public MenuItem find(int itemID) {
        return this.menuRepository.getMenuByID(itemID);

    }

    public boolean printAllMenu() {
        try {
            Set<MenuItem> menu = this.menuRepository.getAllMenu();

            System.out.println("Available Dishes in Menu:");
            for (MenuItem item : menu) {
                System.out.println(item);
            }
            return true;

        } catch (Exception ex) {
            return false;
        }
    }

}


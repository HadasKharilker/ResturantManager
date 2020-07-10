package Controller;


import java.util.Set;

import model.MenuItem;
import model.Service.MenuService;


public class MenuController {

    //for singelton
    private static MenuController INSTANCE;
    private static final Object lockObject = new Object();
    private final MenuService menuService;

    //for singelton
    private MenuController() throws Exception {
        this.menuService = new MenuService();
    }

    public static MenuController getInstance() throws Exception {
        if (INSTANCE == null) {
            synchronized (lockObject) {
                if (INSTANCE == null) {
                    INSTANCE = new MenuController();
                }
            }
        }

        return INSTANCE;
    }


    public boolean addMenuItem(String menuID, String name, String price, String type) {
        if (menuID != "")
            return this.menuService.addNewItemToMenu(menuID, name, price, type);

        return false;
    }


    public boolean editMenuItem(String menuID, String name, String price, String type) {
        if (menuID != "")
            return this.menuService.editMenuItem(menuID, name, price, type);

        return false;
    }

    public boolean deleteMenuItem(String itemID) {
        if (itemID != "")
            return this.menuService.deleteMenuItem(itemID);

        return false;

    }

    public boolean printAllMenu() {
        return menuService.printAllMenu();
    }

    public MenuItem findItem(int itemID) {
        if (itemID != 0)
            return this.menuService.find(itemID);


        return null;
    }
}




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


    public boolean addMenuItem(MenuItem item) {
        // validations
        return this.menuService.addNewItemToMenu(item);
    }

    public Set<MenuItem> getAllMenuItems() {
        Set<MenuItem> menu = this.menuService.getAllMenuItems();
        return menu;
    }

    public boolean editMenuItem(MenuItem item) {


        return this.menuService.editMenuItem(item);


    }
    public boolean deleteMenuItem(Integer itemID) {

        return this.menuService.deleteMenuItem(itemID);

    }


    public MenuItem findItem(int itemID) {
        return  this.menuService.find(itemID);
    }
}




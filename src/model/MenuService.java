package model;


import java.util.Set;

public class MenuService {
    private final MenuRepository menuRepository;


    public MenuService() throws Exception{
        menuRepository = MenuItemRepositoryImpel.getInstance();
    }

    public boolean addNewItemToMenu(MenuItem item) {
        try {
            this.menuRepository.addMenuItem(item);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Set<MenuItem> getAllMenuItems() {
        return this.menuRepository.findAll();
    }


    public boolean editMenuItem(MenuItem item) {
        try {
            this.menuRepository.updateMenuItem(item);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }


    public boolean deleteMenuItem(Integer item) {
        try {
            this.menuRepository.deleteMenuItem(item);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

}


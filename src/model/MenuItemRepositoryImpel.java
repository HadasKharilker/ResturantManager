package model;

import java.io.IOException;

import java.util.Set;

public class MenuItemRepositoryImpel implements MenuRepository {

    private static MenuItemRepositoryImpel INSTANCE;
    private static Object lockObject = new Object();
    private final String FILENAME = "menu";
    private Set<MenuItem> menu;
    private FileManager<MenuItem> fileManager;

    private MenuItemRepositoryImpel() throws IOException, ClassNotFoundException {
        this.fileManager = new FileManager<MenuItem>(FILENAME);
        this.menu = this.fileManager.read();
    }

    //for singelton use
    public static MenuItemRepositoryImpel getInstance() throws Exception {
        if (INSTANCE == null) {
            synchronized (lockObject) {
                if (INSTANCE == null) {
                    INSTANCE = new MenuItemRepositoryImpel();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void addMenuItem(MenuItem menuItem) throws Exception {
        if (menuItem == null) {
            throw new Exception("must have a value");
        }

        if (this.menu.contains(menuItem)) {
            throw new Exception("Already exists!");
        }

        this.menu.add(menuItem);
        this.fileManager.write(this.menu);

    }

    @Override
    public void deleteMenuItem(int itemID) throws Exception {

        MenuItem menuItem = find(itemID);

        if (menuItem == null) {
            throw new Exception("item does'nt exists!");
        }

        this.menu.remove(menuItem);
        this.fileManager.write(this.menu);
    }


    @Override
    public void updateMenuItem(MenuItem menuItem) throws Exception {
        if (menuItem == null) {
            throw new Exception("must have a value");
        }
        if (!(this.menu.contains(menuItem))) {
            throw new Exception("Item does not exists!");
        } else {
            for (MenuItem m : menu) {
                if (m.getItemID() == menuItem.getItemID()) {
                    m.setName(menuItem.getItemName());
                    m.setPrice(menuItem.getPrice());
                    m.setItemType(menuItem.getItemType());

                }
            }

        }
        this.fileManager.write(this.menu);

    }

    @Override
    public MenuItem find(int itemID) {

        for (MenuItem m : menu) {
            if (m.getItemID() == itemID) {
                return m;
            }
        }
        

        return null;
    }

    @Override
    public Set<MenuItem> findAll() {

        return this.menu;
    }

}

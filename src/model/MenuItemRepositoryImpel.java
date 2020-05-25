package model;

import java.io.IOException;

import java.util.Set;

public class MenuItemRepositoryImpel implements MenuRepository {
	private final String FILENAME = "menu";
	private Set<MenuItem> menu;
	private FileManager<MenuItem> fileManager;
	
	public MenuItemRepositoryImpel() throws IOException, ClassNotFoundException {
		this.fileManager = new FileManager<MenuItem>(FILENAME);
		
		this.menu = this.fileManager.read();
		
		
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
	public void deleteMenuItem(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MenuItem find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<MenuItem> findAll() {
		
		return this.menu;
	}

}

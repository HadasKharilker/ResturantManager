package model.Repository;


import model.MenuItem;

import java.util.Set;

public interface MenuRepository {
	
	void addMenuItem(MenuItem menuItem) throws Exception ;
	
	void deleteMenuItem(int id) throws Exception;

	void updateMenuItem(MenuItem menuItem) throws Exception;

	
	MenuItem find(int id);
	
	Set<MenuItem> findAll();
	//edit 
	
	

}
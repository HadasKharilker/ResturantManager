package model;


import java.util.Set;

public interface MenuRepository {
	
	void addMenuItem(MenuItem menuItem) throws Exception ;
	
	void deleteMenuItem(int id);
	
	MenuItem find(int id);
	
	Set<MenuItem> findAll();
	//edit 
	
	

}
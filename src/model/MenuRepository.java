package model;


import java.io.IOException;
import java.util.Set;

public interface MenuRepository {
	
	void addMenuItem(MenuItem menuItem) throws Exception ;
	
	void deleteMenuItem(int id) throws IOException;
	
	MenuItem find(int id);
	
	Set<MenuItem> findAll();
	//edit 
	
	

}
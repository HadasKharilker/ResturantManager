package model;


public class main {

	public static void main(String[] args)  throws Exception {
		
		MenuItem m1=new MenuItem(1,"PIZZA",25.0,MenuItemType.MAIN);
		MenuItem m2=new MenuItem(2,"Cola",10.0,MenuItemType.DRINK);
		MenuItem m3=new MenuItem(3,"PASTA",25.0,MenuItemType.MAIN);
		
		MenuItemRepositoryImpel menu=new MenuItemRepositoryImpel();
		menu.addMenuItem(m1);
		menu.addMenuItem(m2);
		menu.addMenuItem(m3);
		
		System.out.println(menu.findAll());

	}

}

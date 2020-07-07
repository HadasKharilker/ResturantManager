package model;

import java.io.Serializable;


public class MenuItem implements Serializable {
	
	 /**
	 * 
	 */
	
	 private int itemID;
	 private String itemName;
	 private Double price;
	 private MenuItemType itemType;

	 private static final long serialVersionUID = 1L;
	 
	 
	 //c'tor
	public MenuItem(Integer itemID, String itemName, Double price, MenuItemType itemType) {
		super();
		this.itemID = itemID;
		this.itemName = itemName;
		this.price  = price;
		this.itemType=itemType;
	}
	
	

	public MenuItem(Integer itemID) {
		super();
		this.itemID = itemID;
	}



	//setters and getters
	public int getItemID() {
		return itemID;
	}

	public void setItemID(int itemID) {
		this.itemID =itemID;
	}

	public String getItemName() {
		return itemName;
	}

	public void setName(String itemName) {
		this.itemName = itemName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public MenuItemType getItemType() {
		return itemType;
	}

	public void setItemType(MenuItemType itemType) {
		this.itemType=itemType;
	}

	
	
	//
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + itemID;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MenuItem other = (MenuItem) obj;
		if (itemID != other.itemID)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "[id=" + itemID + ", name=" + itemName + ", price=" + price + ", itemType=" + itemType  +"]";
		
	}
	
	

}

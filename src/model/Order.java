package model;
import java.io.Serializable;
import java.util.Set;

public class Order implements Serializable {
    private int orderID;
    private int StaffID ;
    private Set<MenuItem> menuItems;
    private double totalOrderPrice;
    private static final long serialVersionUID = 1L;


}

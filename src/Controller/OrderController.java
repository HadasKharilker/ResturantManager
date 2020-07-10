package Controller;

import model.*;
import model.Service.OrderService;

import java.util.Set;

public class OrderController {

    //for singelton
    private static OrderController INSTANCE;
    private static final Object lockObject = new Object();
    private final OrderService orderService;


    //for singelton
    private OrderController() throws Exception {
        this.orderService = new OrderService();
    }

    public static OrderController getInstance() throws Exception {
        if (INSTANCE == null) {
            synchronized (lockObject) {
                if (INSTANCE == null) {
                    INSTANCE = new OrderController();
                }
            }
        }

        return INSTANCE;
    }

    public boolean viewAllOpenOrders() {
        return this.orderService.viewAllOpenOrders();
    }

    public boolean addOrder(Staff staff, Set<MenuItemOrder> menuItemOrders, String clientID) {
        if (staff != null)
            return this.orderService.addNewOrder(staff, menuItemOrders, clientID);

        return false;


    }

    public boolean viewAllOpenOrdersByStaff(Staff staff) {
        if (staff != null)
            return this.orderService.viewAllOpenOrdersByStaff(staff);

        return false;
    }


    public boolean viewTotalOrderReport() {
        return this.orderService.viewTotalOrderReport();
    }

    public boolean deleteOrder(String orderID) {
        if (orderID != "")
            return this.orderService.deleteOrder(orderID);

        return false;

    }

    public boolean editOrder(String orderID, Set<MenuItemOrder> menuItemOrders, String clientID) {
        if (orderID != "")
            return this.orderService.updateOrder(orderID, menuItemOrders, clientID);

        return false;

    }


    public boolean closeOrder(String orderID) {
        if (orderID != "")
            return this.orderService.closeOrder(orderID);

        return false;

    }


}

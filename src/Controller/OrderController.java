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

    public Boolean closeOrder(Order order, Client clientOrder) throws Exception {
        return this.orderService.closeOrder(order, clientOrder);
    }

    public Order getOrder(int orderID) {
        return this.orderService.getOrder(orderID);
    }


    public boolean viewAllOpenOrders() {
        return this.orderService.viewAllOpenOrders();
    }

    public boolean addOrder(Staff staff, Set<MenuItemOrder> menuItemOrders, String clientID) {
        return this.orderService.addNewOrder(staff, menuItemOrders, clientID);


    }

    public boolean viewAllOpenOrdersByStaff(Staff staff) {
        return this.orderService.viewAllOpenOrdersByStaff(staff);
    }


    public boolean viewTotalOrderReport() {
        return this.orderService.viewTotalOrderReport();
    }

    public boolean deleteOrder(String orderID) {
        return this.orderService.deleteOrder(orderID);


    }

    public boolean editOrder(String orderID, Set<MenuItemOrder> menuItemOrders, String clientID) {

        return this.orderService.updateOrder(orderID,menuItemOrders,clientID);

    }

    public Set<Order> getAllStaffOpenOrders(int staffID) {

        return this.orderService.getAllStaffOpenOrders(staffID);

    }

    public boolean closeOrder(String orderID) {

        return this.orderService.closeOrder(orderID);

    }


}

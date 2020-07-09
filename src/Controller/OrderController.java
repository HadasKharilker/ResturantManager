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

    public Set<Order> getAllOpenOrders() {
        Set<Order> order = this.orderService.getAllOpenOrders();
        return order;
    }

    public Set<Order> getAllClosedOrders() {
        return this.orderService.getAllClosedOrders();
    }

    public boolean addOrder(Order newOrder) {
        return this.orderService.addNewOrder(newOrder);


    }

    public boolean deleteOrder(int orderID) {
        return this.orderService.deleteOrder(orderID);


    }

    public boolean editOrder(Order editOrder, String orderID) {
        editOrder.setOrderID(Integer.parseInt(orderID));
        return this.orderService.updateOrder(editOrder);

    }

    public Set<Order> getAllStaffOpenOrders(int staffID) {

        return this.orderService.getAllStaffOpenOrders(staffID);

    }

}

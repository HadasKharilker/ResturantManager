package Controller;
import model.*;

import java.util.HashSet;
import java.util.Scanner;
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


    public Set<Order> getAllOpenOrders() {
        Set<Order> order = this.orderService.getAllOpenOrders();
        return order;
    }


    public boolean addOrder(Order newOrder) {
        return this.orderService.addNewOrder(newOrder);


    }

    public boolean editOrder(Order editOrder, String orderID) {
        editOrder.setOrderID(Integer.parseInt(orderID));
        return this.orderService.updateOrder(editOrder);

    }
}

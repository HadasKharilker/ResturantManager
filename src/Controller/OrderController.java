package Controller;
import model.MenuItem;
import model.MenuService;
import model.Order;
import model.OrderService;

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



}

package model;

import java.io.IOException;
import java.util.Set;

public interface OrderRepository {
    void addOrder(Order order) throws Exception;

    void deleteOrder(int orderID) throws IOException;

    Order getOrder(int orderID);

    Set<Order> getAllOpenOrders();

    Set<Order> getAllStaffOpenOrders(int staffID);

    void updateOrder(Order order) throws Exception;

    void closeOrder(Order order) throws IOException;

    public Set<Order> getAllClosedOrders();
}

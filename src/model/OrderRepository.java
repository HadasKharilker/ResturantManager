package model;

import java.io.IOException;
import java.util.Set;

public interface OrderRepository {
    void addOrder(Order order) throws Exception ;

    void deleteOrder(int id) throws IOException;

    Set<Order> getAllOrders() ;
}

package model;

import java.util.Set;

public class OrderService {

    private final OrderRepository orderRepository;


    public OrderService() throws Exception {
        orderRepository = OrderRepositoryImpel.getInstance();
    }

    public Set<Order> getAllOpenOrders() {
        return this.orderRepository.getAllOpenOrders();
    }

    public Set<Order> getAllClosedOrders() {
        return this.orderRepository.getAllClosedOrders();
    }

    public boolean deleteOrder(int orderID) {
        try {
            this.orderRepository.deleteOrder(orderID);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public Order getOrder(int orderID) {
        try {
            return this.orderRepository.getOrder(orderID);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public Boolean closeOrder(Order order, Client clientOrder) throws Exception {
        try {
            this.orderRepository.closeOrder(order, clientOrder);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }

    }

    public boolean addNewOrder(Order newOrder) {
        try {
            this.orderRepository.addOrder(newOrder);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean updateOrder(Order editOrder) {
        try {
            this.orderRepository.updateOrder(editOrder);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

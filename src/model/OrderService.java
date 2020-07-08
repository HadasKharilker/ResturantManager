package model;
import java.util.Set;

public class OrderService {

    private final OrderRepository orderRepository;


    public OrderService() throws Exception{
        orderRepository = OrderRepositoryImpel.getInstance();
    }

    public Set<Order> getAllOpenOrders() {
        return this.orderRepository.getAllOpenOrders();
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

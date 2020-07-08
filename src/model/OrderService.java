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









}

package model;

import java.io.IOException;
import java.util.Set;

public class OrderRepositoryImpel implements OrderRepository {

    private final String FILENAME = "order";
    private Set<Order> orders;
    private FileManager<Order> fileManager;

    public OrderRepositoryImpel() throws IOException, ClassNotFoundException {
        this.fileManager = new FileManager<Order>(FILENAME);
        this.orders = this.fileManager.read();
    }

    @Override
    public void addOrder(Order order) throws Exception {
        if (order == null) {
            throw new Exception("must have a value");
        }

        int maxOrderID=1;
        for (Order orderItem : orders) {
        if(orderItem.getOrderID()>maxOrderID)
            maxOrderID=orderItem.getOrderID();
        }
        order.setOrderID(maxOrderID+1);

        this.orders.add(order);
        this.fileManager.write(this.orders);
    }

    @Override
    public void deleteOrder(int id) throws IOException {

    }

    @Override
    public Set<Order> getAllOrders()  {
        return this.orders;
    }
}

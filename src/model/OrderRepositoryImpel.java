package model;

import java.io.IOException;
import java.util.Set;

public class OrderRepositoryImpel implements OrderRepository {

    //for singelton
    private static OrderRepositoryImpel INSTANCE ;
    private static Object lockObject = new Object();

    private final String FILENAME = "order";
    private Set<Order> orders;
    private FileManager<Order> fileManager;

    //singelton has a private constructor
    private OrderRepositoryImpel() throws IOException, ClassNotFoundException {
        this.fileManager = new FileManager<Order>(FILENAME);
        this.orders = this.fileManager.read();
    }

    //for singelton use
    public static OrderRepositoryImpel getInstance() throws Exception{
        if( INSTANCE == null) {
            synchronized (lockObject) {
                if (INSTANCE == null) {
                    INSTANCE = new OrderRepositoryImpel();
                }
            }
        }
        return INSTANCE;
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

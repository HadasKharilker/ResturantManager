package model.Repository;

import model.Client;
import model.FileManager;
import model.Mail;
import model.Order;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class OrderRepositoryImpel implements OrderRepository {

    //for singelton
    private static OrderRepositoryImpel INSTANCE;
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
    public static OrderRepositoryImpel getInstance() throws Exception {
        if (INSTANCE == null) {
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
        try {
            if (order == null) {
                throw new Exception("must have a value");
            }

            int newOrderID = getNewOrderID();
            order.setOrderID(newOrderID);

            this.orders.add(order);
            this.fileManager.write(this.orders);

        } catch (Exception ex) {
            throw new Exception("error in add order");
        }
    }

    private int getNewOrderID() {
        int maxOrderID = 0;
        for (Order orderItem : orders) {
            if (orderItem.getOrderID() > maxOrderID)
                maxOrderID = orderItem.getOrderID();
        }

        return maxOrderID + 1;

    }

    @Override
    public void deleteOrder(int orderID) throws IOException {
        try {
            Order order = getOrder(orderID);

            if (order == null)
                throw new IOException("order not exist!");

            orders.remove(order);
            this.fileManager.write(this.orders);

        } catch (IOException ex) {
            throw new IOException("error in delete order");
        }
    }

    @Override
    public void closeOrder(Order order) throws Exception {
        try {
            if (order == null)
                throw new IOException("order not exist!");

            for (Order o : orders) {
                if (o.getOrderID() == order.getOrderID()) {
                    o.setClosed(true);
                }
            }


            this.fileManager.write(this.orders);


        } catch (IOException ex) {
            throw new IOException("error in close order");
        }
    }


    @Override
    public Order getOrder(int orderID) {

        for (Order o : orders) {
            if (o.getOrderID() == orderID) {
                return o;
            }

        }
        return null;
    }


    @Override
    public Set<Order> getAllOpenOrders() {
        Set<Order> openOrders = new HashSet<Order>();

        for (Order o : orders) {
            if (!o.isClosed()) {
                openOrders.add(o);
            }

        }
        return openOrders;
    }

    @Override
    public Set<Order> getAllStaffOpenOrders(int staffID) {
        Set<Order> ordersByStaff = new HashSet<Order>();

        for (Order o : orders) {
            if (o.getStaffID() == staffID) {
                if (!o.isClosed()) {
                    ordersByStaff.add(o);
                }
            }

        }
        return ordersByStaff;
    }

    @Override
    public Set<Order> getAllClosedOrders() {
        Set<Order> openOrders = new HashSet<Order>();

        for (Order o : orders) {
            if (o.isClosed()) {
                openOrders.add(o);
            }

        }
        return openOrders;
    }

    @Override
    public void updateOrder(Order order) throws Exception {
        if (order == null) {
            throw new Exception("must have a value");
        }
        if (!(this.orders.contains(order))) {
            throw new Exception("Item does not exists!");
        } else {
            for (Order o : orders) {
                if (o.getOrderID() == order.getOrderID()) {
                    o.setMenuItems(order.getMenuItems());
                    o.setClientID(order.getClientID());
                }
            }

        }
        this.fileManager.write(this.orders);

    }
}

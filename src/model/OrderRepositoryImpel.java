package model;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class OrderRepositoryImpel implements OrderRepository {

    //for singelton
    private static OrderRepositoryImpel INSTANCE;
    private static Object lockObject = new Object();

    private final String FILENAME = "order";
    private Set<Order> openOrders;
    private Set<Order> closedOrders;
    private FileManager<Order> fileManager;

    //singelton has a private constructor
    private OrderRepositoryImpel() throws IOException, ClassNotFoundException {
        this.fileManager = new FileManager<Order>(FILENAME);
        this.openOrders = this.fileManager.read();
        this.closedOrders = this.fileManager.read();
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

            this.openOrders.add(order);
            this.fileManager.write(this.openOrders);

        } catch (Exception ex) {
            throw new Exception("error in add order");
        }
    }

    private int getNewOrderID() {
        int maxOrderID = 0;
        for (Order orderItem : openOrders) {
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

            openOrders.remove(order);
            this.fileManager.write(this.openOrders);

        } catch (IOException ex) {
            throw new IOException("error in delete order");
        }
    }

    @Override
    public void closeOrder(Order order, Client clientOrder) throws Exception {
        try {
        if (order == null)
            throw new IOException("order not exist!");

        if (clientOrder != null) {
            String messageClosed = "order closed , total price:" + order.getTotalPriceOrder();
            messageClosed += "credit details :" + clientOrder.getCreditDetails().toString();
            Mail.sendMail(messageClosed, clientOrder.getMailAddress());
        }

        deleteOrder(order.getOrderID());
        addOrderToClosedList(order);

        } catch (IOException ex) {
            throw new IOException("error in close order");
        }
    }

    private void addOrderToClosedList(Order order) throws IOException {
        this.closedOrders.add(order);
        this.fileManager.write(this.closedOrders);
    }

    @Override
    public Order getOrder(int orderID) {

        for (Order o : openOrders) {
            if (o.getOrderID() == orderID) {
                return o;
            }

        }
        return null;
    }


    @Override
    public Set<Order> getAllOpenOrders() {
        return this.openOrders;
    }

    @Override
    public Set<Order> getAllStaffOpenOrders(int staffID) {
        Set<Order> ordersByStaff = new HashSet<Order>();

        for (Order o : openOrders) {
            if (o.getStaffID() == staffID) {
                ordersByStaff.add(o);
            }

        }
        return ordersByStaff;
    }

    @Override
    public Set<Order> getAllClosedOrders() {
        return this.closedOrders;
    }

    @Override
    public void updateOrder(Order order) throws Exception {
        if (order == null) {
            throw new Exception("must have a value");
        }
        if (!(this.openOrders.contains(order))) {
            throw new Exception("Item does not exists!");
        } else {
            for (Order o : openOrders) {
                if (o.getOrderID() == order.getOrderID()) {
                    deleteOrder(o.getOrderID());
                    addOrder(o);
                    //o.setMenuItems(order.getMenuItems());
                    //o.setTotalOrderPrice(order.getTotalOrderPrice());
                }
            }

        }
        this.fileManager.write(this.openOrders);

    }
}

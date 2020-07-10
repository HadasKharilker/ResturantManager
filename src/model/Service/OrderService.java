package model.Service;

import Controller.ClientController;
import Controller.MenuController;
import View.MenuView;
import model.Client;
import model.MenuItemOrder;
import model.Order;
import model.Repository.ClientRepository;
import model.Repository.ClientRepositoryImpel;
import model.Repository.OrderRepositoryImpel;
import model.Repository.OrderRepository;
import model.Staff;

import java.util.Scanner;
import java.util.Set;

public class OrderService {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;

    public OrderService() throws Exception {
        orderRepository = OrderRepositoryImpel.getInstance();
        clientRepository = ClientRepositoryImpel.getInstance();
    }


    public boolean viewAllOpenOrders() {
        try {
            Set<Order> orders = this.orderRepository.getAllOpenOrders();

            for (Order o : orders) {
                System.out.println(o);
            }

            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean viewAllOpenOrdersByStaff(Staff staff) {
        try {
            Set<Order> orders = this.orderRepository.getAllStaffOpenOrders(staff.getPersonId());

            for (Order o : orders) {
                System.out.println(o);
            }

            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }


    public boolean viewTotalOrderReport() {
        try {
            Set<Order> orders = this.orderRepository.getAllClosedOrders();
            double sumReport = 0;

            for (Order o : orders) {
                System.out.println(o);
                sumReport += o.getTotalPriceOrder();
            }
            System.out.println("total orders price:" + sumReport);
            return true;


        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean deleteOrder(String orderID) {
        try {
            this.orderRepository.deleteOrder(Integer.parseInt(orderID));
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


    public boolean closeOrder(String orderID) {

        try {
            Order orderToClose = this.orderRepository.getOrder(Integer.parseInt(orderID));
            orderToClose.setClosed(true);
            System.out.println("Total Order price: " + orderToClose.getTotalPriceOrder());

            Client client = this.clientRepository.getClient(orderToClose.getClientID());
            orderRepository.closeOrder(orderToClose, client);

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean addNewOrder(Staff staff, Set<MenuItemOrder> menuItemOrders, String clientID) {
        try {
            Order newOrder;
            if (clientID != "")
                newOrder = new Order(staff.getPersonId(), menuItemOrders, Integer.parseInt(clientID));

            else
                newOrder = new Order(staff.getPersonId(), menuItemOrders);

            this.orderRepository.addOrder(newOrder);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean updateOrder(String orderID, Set<MenuItemOrder> menuItemOrders, String clientID) {
        try {
            Order editOrder = this.orderRepository.getOrder(Integer.parseInt(orderID));

            editOrder.setClientID(Integer.parseInt(clientID));
            editOrder.setMenuItems(menuItemOrders);

            this.orderRepository.updateOrder(editOrder);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Set<Order> getAllStaffOpenOrders(int staffID) {
        try {
            return this.orderRepository.getAllStaffOpenOrders(staffID);


        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }

    }
}

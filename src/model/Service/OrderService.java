package model.Service;

import Controller.ClientController;
import Controller.MenuController;
import View.MenuView;
import model.*;
import model.Repository.*;

import java.util.Scanner;
import java.util.Set;

public class OrderService {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final MenuRepository menuRepository;

    public OrderService() throws Exception {
        orderRepository = OrderRepositoryImpel.getInstance();
        clientRepository = ClientRepositoryImpel.getInstance();
        menuRepository = MenuItemRepositoryImpel.getInstance();
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
                sumReport += getTotalPriceOrder(o);
            }
            System.out.println("total orders price:" + sumReport);
            return true;


        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public double getTotalPriceOrder(Order order) {

        double totalPrice = 0;

        for (MenuItemOrder menuItemOrder : order.getMenuItems()) {
            String menuItemID = menuItemOrder.getMenuItemID();
            MenuItem menuItem = menuRepository.getMenuByID(Integer.parseInt(menuItemID));

            double menuItemPrice = menuItem.getPrice();
            int numberOfItems = menuItemOrder.getNumberOfItem();

            totalPrice += menuItemPrice * numberOfItems;
        }

        return totalPrice;

    }

    public boolean deleteOrder(String orderID) {
        try {
            this.orderRepository.deleteOrder(Integer.parseInt(orderID));
            return true;
        } catch (Exception e) {

            return false;
        }

    }

    public Order getOrder(int orderID) {
        try {
            return this.orderRepository.getOrder(orderID);

        } catch (Exception e) {

            return null;
        }

    }


    public boolean closeOrder(String orderID) {

        try {
            Order orderToClose = this.orderRepository.getOrder(Integer.parseInt(orderID));
            orderToClose.setClosed(true);
            System.out.println("Total Order price: " + getTotalPriceOrder(orderToClose));

            Client clientOrder = this.clientRepository.getClient(orderToClose.getClientID());
            orderRepository.closeOrder(orderToClose);

            String messageClosed = "order closed , total price:" + getTotalPriceOrder(orderToClose);
            messageClosed += "credit details :" + clientOrder.getCreditDetails().toString();
            Mail.sendMail(messageClosed, clientOrder.getMailAddress());


            return true;

        } catch (Exception e) {

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

            return false;
        }
    }

    public Set<Order> getAllStaffOpenOrders(int staffID) {
        try {
            return this.orderRepository.getAllStaffOpenOrders(staffID);


        } catch (Exception e) {

            return null;

        }

    }
}

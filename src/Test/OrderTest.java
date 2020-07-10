package Test;

import Controller.OrderController;

import model.MenuItem;
import model.MenuItemType;
import model.Order;

import model.StaffHour;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {


    private OrderController orderController;


    @BeforeEach
    public void setup() throws Exception {
        orderController = OrderController.getInstance();

    }

    @AfterAll
    public void tearDown() {
        System.out.println("OrderTestsEnded");
    }
/*
    @Test


    public void FailViewAllOpenOrdersByEmptyIdTest() {


        Assertions.assertThrows(Exception.class, () -> {

            Set<Order> result = orderController.getAllStaffOpenOrders(Integer.parseInt(""));
            Assertions.assertNull(result);

        });
    }

@Test
        public void SuccessViewAllOpenOrdersByIdTest() {


            Assertions.assertThrows(Exception.class, () -> {

                Set<Order> result = orderController.getAllStaffOpenOrders(1);
                Assertions.assertNull(result);

            });



*/


}




package org.teamwe.carrent.serviceImpl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.teamwe.carrent.entity.Order;

import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderServiceImplTest {

    @Autowired
    private  OrderServiceImpl orderService;
    @Test
    public void makeOrder() {

        long time = System.currentTimeMillis() - 3600000;

        orderService.makeOrder("123.com","陕V123456",time,time);
    }

    @Test
    public void getOrders() {
        List<Order> orders = orderService.getOrders("123.com");

        for (Iterator<Order> iterator = orders.iterator();iterator.hasNext();){
            Order order = iterator.next();
            System.out.println(order.toString());
        }
    }

    @Test
    public void finishOrder() {
        orderService.finishOrder("4",1);
    }

    @Test
    public void deleteOrder() {
        orderService.deleteOrder("4");
    }

    @Test
    public void getById() {
       Order order =  orderService.getById("4");
        System.out.println(order.toString());
    }

    @Test
    public void endOrder() {
        orderService.endOrder("4");
    }

    @Test
    public void payOrder() {
        orderService.payOrder("4");
    }
}
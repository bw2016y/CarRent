package org.teamwe.carrent.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.teamwe.carrent.dao.CarDAO;
import org.teamwe.carrent.dao.OrderDAO;
import org.teamwe.carrent.entity.Car;
import org.teamwe.carrent.entity.Order;
import org.teamwe.carrent.service.OrderService;
import org.teamwe.carrent.utils.ReturnStatus;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    @Autowired
    private CarDAO carDAO;
    @Autowired
    private OrderDAO orderDAO;

    /**
     * @param email     客户id   available     int     非空 0 可用  1不可用
     * @param card      车牌号    status        int     非空 0逻辑存在 1逻辑删除
     * @param timebegin 开始租车时间
     * @param timeend   结束租车时间
     * @return {@link org.teamwe.carrent.utils.ReturnStatus}
     */
    @Override
    public int makeOrder(String email, String card, long timebegin, long timeend) {

        Car car = carDAO.get_car(card);
        if(car.getAvailable() == 1){
            System.out.println("车辆已被使用，不可生成订单");
            return ReturnStatus.FAILURE;//车辆已被使用，不可生成订单
        }

       // long timebegin = System.currentTimeMillis();

        long timeendr = timeend - timebegin;


        Order order = new Order(0,email,card,timebegin,timeend,timeendr," ",0);

        if(orderDAO.add_Order(order) < 0){//插入一条订单记录
            return ReturnStatus.FAILURE;
        }

        car.setAvailable(1);//将车辆状态设置为不可用
        carDAO.update_car(car);

        System.out.println("生成订单成功"+order.toString());

        return ReturnStatus.SUCCESS;
    }

    /**
     * @param email 用户id
     * @return 用户所有的订单列表
     */
    @Override
    public List<Order> getOrders(String email) {


        List<Order> orders = orderDAO.get_Orders_by_email(email);
        System.out.println("该用户所有订单："+orders.toString());
        return orders;
    }

    @Override
    public int finishOrder(String orderId, int level) {
        return 0;
    }



    /**
     * 取消订单
     *
     * @param orderId 订单id
     * @return {@link org.teamwe.carrent.utils.ReturnStatus}
     */
    @Override
    public int deleteOrder(String orderId) {
        Order order = orderDAO.get_order(Integer.parseInt(orderId));
        order.setStatus(1);//1表示逻辑删除

        if (orderDAO.update_order(order) < 0){
            return ReturnStatus.FAILURE;
        }

        return ReturnStatus.SUCCESS;
    }

    @Override
    public Order getById(String orderId) {
        return null;
    }

    @Override
    public int endOrder(String orderId) {
        return 0;
    }

    @Override
    public int payOrder(String orderId) {
        return 0;
    }
}

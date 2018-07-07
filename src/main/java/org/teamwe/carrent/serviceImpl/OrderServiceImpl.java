package org.teamwe.carrent.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.teamwe.carrent.dao.CarDAO;
import org.teamwe.carrent.dao.OrderDAO;
import org.teamwe.carrent.dao.UserDAO;
import org.teamwe.carrent.entity.Car;
import org.teamwe.carrent.entity.Order;
import org.teamwe.carrent.entity.User;
import org.teamwe.carrent.service.OrderService;
import org.teamwe.carrent.utils.ReturnStatus;

import java.util.Iterator;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CarDAO carDAO;
    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private UserDAO userDAO;

    /**
     * @param email     客户id   available     int     非空 0 可用  1不可用
     * @param card      车牌号    status        int     非空 0逻辑存在 1逻辑删除
     * @param timebegin 开始租车时间
     * @param timeend   结束租车时间
     * @return {@link org.teamwe.carrent.utils.ReturnStatus}
     */
    @Override
    public String makeOrder(String email, String card, long timebegin, long timeend) {


        Car car = carDAO.get_car(card);
        if(car.getAvailable() == 1){
            System.out.println("车辆已被使用，不可生成订单");
            return null;//车辆已被使用，不可生成订单
        }

        if(userDAO.Get_userByEmial(email).getIsvalidated() == 0){
            System.out.println("该用户已被封号，无法生成订单");
            return null;
        }

       // long timebegin = System.currentTimeMillis();

        long timeendr = 0;


        Order order = new Order(0,email,card,timebegin,timeend,timeendr," ",0);

        if(orderDAO.add_Order(order) < 0){//插入一条订单记录
            return null;
        }

        car.setAvailable(1);//将车辆状态设置为不可用
        carDAO.update_car(car);

        System.out.println("生成订单成功"+order.toString());

        String id = String.valueOf(order.getOrderid());
        return id;
    }

    /**
     * @param email 用户id
     * @return 用户所有的订单列表
     */
    @Override
    public List<Order> getOrders(String email) {


        List<Order> orders = orderDAO.get_Orders_by_email(email);
        for (Iterator<Order> orderIterator = orders.iterator();orderIterator.hasNext();){
            Order order = orderIterator.next();
            if(order.getStatus() == 1)
                orderIterator.remove();
        }
        return orders;
    }
    /**
     * 技师查阅并且用户已支付之后由技师调用
     *
     * @param orderId 订单id
     * @param level   技师对订单的评级, 供信用分和积分的加减
     * @return {@link org.teamwe.carrent.utils.ReturnStatus}
     */

    /*

      技师对订单的评
    int BEST = 0;
    int GOOD = 1;
    int WELL = 2;
    int MID = 3;
    int BAD = 4;
    int WORSE = 5;
     */
    @Override
    public int finishOrder(String orderId, int level) {
        if(orderDAO.get_order(Integer.parseInt(orderId)) == null){
            System.out.println("订单不存在");
            return ReturnStatus.FAILURE;

        }

        Order order = orderDAO.get_order(Integer.parseInt(orderId));


        User user = userDAO.Get_userByEmial(order.getEmail());




        if(order.getType() == 2){
            int credit = user.getCredit();
            int points = user.getPoints();

            points += order.getMoney();

            switch (level){
                case OrderService.BEST :
                    credit += 5;
                    break;
                case OrderService.GOOD :
                    credit += 3;
                    break;
                case OrderService.WELL :
                    credit += 2;
                    break;
                case OrderService.MID:
                    credit += 1;
                    break;
                case OrderService.BAD:
                    credit -= 2;
                    break;
                case OrderService.WORSE:
                    credit -= 10;
                    break;

            }

            user.setCredit(credit);
            user.setPoints(points);

            if(credit < 0){
                user.setIsvalidated(0);//用户信用分过低的话，封号
            }
            userDAO.Update_user(user);//根据此订单，完成用户的信用和积分的更新



            order.setType(3);
            orderDAO.update_order(order);

        }
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
        if(order.getType() != 3){
            System.out.println("订单尚未完成，无法评价");
            return  ReturnStatus.FAILURE;
        }

        order.setStatus(1);//1表示逻辑删除

        if (orderDAO.update_order(order) < 0){
            return ReturnStatus.FAILURE;
        }

        return ReturnStatus.SUCCESS;
    }

    /**
     * 根据订单id获取
     *
     * @param orderId 订单id
     * @return 订单
     */
    @Override
    public Order getById(String orderId) {
       Order order =  orderDAO.get_order(Integer.parseInt(orderId));
        System.out.println(order.toString());

        return order;
    }

    /**
     * 技师结束用车, 停止计时
     *
     * @param orderId 订单id
     * @return 是否成功
     */
    @Override
    public int endOrder(String orderId) {
        Order order = orderDAO.get_order(Integer.parseInt(orderId));

        if(order.getType() == 0){
            order.setTimeende(System.currentTimeMillis());
            order.setType(1);

            long time = order.getTimeende() - order.getTimebegin();

            int day= 1 + (int) (time/86400000);
            Car car = carDAO.get_car(order.getCard());
            car.setAvailable(0);

            order.setMoney(day*car.getPrice());

            System.out.println("技师结束用车计时，系统生成订单金额");
            System.out.println(order.toString());

            orderDAO.update_order(order);
            carDAO.update_car(car);//将车辆变为可用状态

        }

        return ReturnStatus.SUCCESS;
    }

    /**
     * 用户支付完成后调用
     *
     * @param orderId 订单id
     * @return 是否成功
     */
    @Override
    public int payOrder(String orderId) {
        Order order = orderDAO.get_order(Integer.parseInt(orderId));

        if(order.getType() == 1){
            order.setType(2);

            //System.out.println(order.getCard());
            Car car = carDAO.get_car(order.getCard());//获得车辆信息
            //System.out.println(car.getEmail());
            User user = userDAO.Get_userByEmial(car.getEmail());//通过车辆信息，获得车主信息


            long money = order.getMoney();
            long balance = user.getBalance();
            balance += money*0.9;
            user.setBalance(balance);//更新车主的余额
            userDAO.Update_user(user);

            orderDAO.update_order(order);


            System.out.println("用户付款完成，车主获得盈利，等待技师审核结束订单");
        }


        return ReturnStatus.SUCCESS;
    }

    @Override
    public List<Order> getUnfinishedOrder() {
        return orderDAO.get_unfinished_orders();
    }

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
    }
}

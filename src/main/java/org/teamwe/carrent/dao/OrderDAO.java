package org.teamwe.carrent.dao;

import org.teamwe.carrent.entity.Order;

import java.util.List;

public interface OrderDAO {
    /**
     *   新建一条订单记录
     * @param order
     * @return
     */
    public   int  add_Order(Order order);


    /**
     *  返回某用户的所有订单记录（逻辑存在）
     * @param email
     * @return
     */
    public List<Order> get_Orders_by_email(String email);

    /**
     * 根据order id  返回一个order记录
     * @param orderid
     * @return
     */
    public Order get_order(int  orderid );

    /**
     *  更新一条订单记录
     * @param order
     * @return
     */
    public  int  update_order(Order  order);




    /**
     *  返回某车辆的所有订单记录
     * @param card
     * @return
     */
    public List<Order> get_Orders_by_card(String card);


    /**
     * 返回所有type 为0 的订单
     * @return
     */
    public List<Order> get_unfinished_orders();


}

package org.teamwe.carrent.service;

import org.teamwe.carrent.entity.Order;

import java.util.List;

/**
 * @author FDws
 * Created on 2018/6/12 10:24
 */

public interface OrderService {
    /**
     *
     * @param email 客户id
     * @param card  车牌号
     * @param timebegin   开始租车时间
     * @param timeend     结束租车时间
     * @return  {@link org.teamwe.carrent.utils.ReturnStatus}
     */
    int makeOrder(String email,String card,long timebegin,long timeend);

    /**
     *
     * @param email 用户id
     * @return 用户所有的订单列表
     */
    List<Order> getOrders(String email);

    /**
     *
     * @param orderId 订单id
     * @return {@link org.teamwe.carrent.utils.ReturnStatus}
     */
    int finishOrder(String orderId);
    /**
     * 取消订单
     * @param orderId 订单id
     * @return {@link org.teamwe.carrent.utils.ReturnStatus}
     */
    int deleteOrder(String orderId);

}

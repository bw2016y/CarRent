package org.teamwe.carrent.service;

import org.teamwe.carrent.entity.Order;

import java.util.List;

/**
 * @author FDws
 * Created on 2018/6/12 10:24
 */

@SuppressWarnings("unused")
public interface OrderService {
    /**
     * 技师对订单的评级
     */
    int BEST = 0;
    int GOOD = 1;
    int WELL = 2;
    int MID = 3;
    int BAD = 4;
    int WORSE = 5;

    /**
     * @param email     客户id
     * @param card      车牌号
     * @param timebegin 开始租车时间
     * @param timeend   结束租车时间
     * @return 返回订单id
     */
   String makeOrder(String email, String card, long timebegin, long timeend);

    /**
     * @param email 用户id
     * @return 用户所有的订单列表
     */
    List<Order> getOrders(String email);

    /**
     * 技师查阅并且用户已支付之后由技师调用
     *
     * @param orderId 订单id
     * @param level   技师对订单的评级, 供信用分和积分的加减
     * @return {@link org.teamwe.carrent.utils.ReturnStatus}
     */
    int finishOrder(String orderId, int level);

    /**
     * 取消订单
     *
     * @param orderId 订单id
     * @return {@link org.teamwe.carrent.utils.ReturnStatus}
     */
    int deleteOrder(String orderId);

    /**
     * 根据订单id获取
     *
     * @param orderId 订单id
     * @return 订单
     */
    Order getById(String orderId);

    /**
     * 技师结束用车, 停止计时
     *
     * @param orderId 订单id
     * @return 是否成功
     */
    int endOrder(String orderId);

    /**
     * 用户支付完成后调用
     *
     * @param orderId 订单id
     * @return 是否成功
     */
    int payOrder(String orderId);

    /**
     * 返回未完成订单列表
     * @return
     */
    List<Order> getUnfinishedOrder();

}

package org.teamwe.carrent.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.teamwe.carrent.dao.OrderDAO;
import org.teamwe.carrent.entity.Order;
import org.teamwe.carrent.service.CommentService;
import org.teamwe.carrent.utils.ReturnStatus;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private OrderDAO orderDAO;



    /**
     * @param orderId 订单id
     * @param comment 用户评论
     * @return {@link org.teamwe.carrent.utils.ReturnStatus}
     */
    @Override
    public int comment(String orderId, String comment) {


       Order order = orderDAO.get_order(Integer.parseInt(orderId));

       if (order.getType() != 3){
           System.out.println("订单尚未完成");
           return ReturnStatus.FAILURE;
       }
       order.setComment(comment);
       order.setTimeendr(System.currentTimeMillis());//评论时间

       if(orderDAO.update_order(order) < 0){
           return ReturnStatus.FAILURE;
       }

        return ReturnStatus.SUCCESS;
    }

    /**
     * @param card 车牌号
     * @return 用户名、评论时间、评论内容的列表
     */
    @Override
    public List<String[]> getComments(String card) {

        List<Order> orders = orderDAO.get_Orders_by_card(card);

        List<String[]> list = new ArrayList<String[]>();

        for (Iterator<Order> orderIterator = orders.iterator();orderIterator.hasNext();){
            Order order = orderIterator.next();

            String[] strings = new String[3];
            strings[0] = order.getEmail();
            strings[1] = String.valueOf(order.getTimeendr());
            strings[2] = order.getComment();

            list.add(strings);


        }


        return list;
    }
}

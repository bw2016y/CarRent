package org.teamwe.carrent.service;

import java.util.List;

/**
 * @author FDws
 * Created on 2018/6/12 10:35
 */

public interface CommentService {
    /**
     *
     * @param orderId 订单id
     * @param comment  用户评论
     * @return {@link org.teamwe.carrent.utils.ReturnStatus}
     */
    int comment(String orderId,String comment);

    /**
     *
     * @param card 车牌号
     * @return 用户名、评论时间、评论内容的列表
     */
    List<String[]> getComments(String card);
}

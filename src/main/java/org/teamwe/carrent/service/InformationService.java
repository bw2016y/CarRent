package org.teamwe.carrent.service;

import org.teamwe.carrent.entity.User;

/**
 * @author FDws
 * Created on 2018/6/11 17:35
 */

public interface InformationService {
    /**
     * 获取用户实体类
     * @param email 用户登陆email
     * @return 用户实体类， null时表示请求失败
     */
    User info(String email);

    /**
     *
     * @param user 更新后的User
     * @return {@link org.teamwe.carrent.utils.ReturnStatus}
     */
    int changeInfo(User user);

    /**
     * 删除用户
     * @param email 用户Email
     * @return {@link org.teamwe.carrent.utils.ReturnStatus}
     */
    int delete(String email);
}

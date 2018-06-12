package org.teamwe.carrent.service;

import org.teamwe.carrent.entity.User;

/**
 * @author FDws
 * Created on 2018/6/11 17:35
 */

public interface InformationService {
    /**
     * 获取用户实体类
     *
     * @param email 用户登陆email
     * @return 用户实体类， null时表示请求失败
     */
    User info(String email);

    /**
     * @param user 更新后的User
     * @return {@link org.teamwe.carrent.utils.ReturnStatus}
     */
    int changeInfo(User user);

    /**
     * 删除用户
     *
     * @param email 用户Email
     * @return {@link org.teamwe.carrent.utils.ReturnStatus}
     */
    int delete(String email);

    /**
     * 忘记密码之后使用邮箱重置， 此方法调用应该向email发送重置密码的链接
     *
     * @param email 用户email
     * @return 是否请求成功 {@link org.teamwe.carrent.utils.ReturnStatus}
     */
    int forgetPassword(String email);

    /**
     * 重置密码请求
     *
     * @param hash     发送到用户邮箱的序列码
     * @param password 用户重置的密码
     * @return 重置是否成功 {@link org.teamwe.carrent.utils.ReturnStatus}
     */
    int resetPassword(String hash, String password);
}

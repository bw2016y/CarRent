package org.teamwe.carrent.service;

/**
 * @author FDws
 * Created on 2018/6/11 17:29
 */

public interface LoginService {
    /**
     *
     * @param email 用户登陆email
     * @param password 用户哈希之后的密码
     * @return 成功或者
     */
    String login(String email, String password);
}

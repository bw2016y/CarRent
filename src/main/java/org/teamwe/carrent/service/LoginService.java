package org.teamwe.carrent.service;

/**
 * @author FDws
 * Created on 2018/6/11 17:29
 */

public interface LoginService {
    /**
     *
     * @param email 用户登陆email
     * @param password 用户的密码, 未哈希过
     * @return 成功或者
     */
    String login(String email, String password);
}

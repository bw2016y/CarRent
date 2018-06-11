package org.teamwe.carrent.service;

import java.util.Map;

/**
 * @author FDws
 * Created on 2018/6/11 17:29
 */

public interface LoginService {
    /**
     *
     * @param email
     * @param password
     * @return
     */
    String login(String email, String password);

    /**
     * 在登录成功之后， 请求用户类型与头像哈希值
     * @param email 登录email
     * @return {"type": 0, "hash": ""}
     */
    Map<String, Object> head(String email);
}

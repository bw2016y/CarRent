package org.teamwe.carrent.service;

/**
 * @author FDws
 * Created on 2018/6/11 17:29
 */

public interface LoginService {
    /**
     * @param email    用户登陆email
     * @param password 用户的密码, 未哈希过
     * @return 长度为1的数组， 表示登录失败， 存储的是失败信息
     * 长度为3 的数组表示登录成功， 第一个为用户名， 第二个为头像哈希码, 第三个Type
     */
    String[] login(String email, String password);
}

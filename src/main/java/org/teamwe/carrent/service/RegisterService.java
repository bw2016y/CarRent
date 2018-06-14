package org.teamwe.carrent.service;

/**
 * @author FDws
 * Created on 2018/6/11 17:14
 */

public interface RegisterService {
    /**
     * @param name     注册用户名
     * @param email    注册邮箱
     * @param password 密码, 未哈希过
     * @param license  驾照条码
     * @param head     头像哈希值
     * @param type     用户类型
     * @param phone    电话， 可选
     * @return 空或者null表示注册成功， 否则注册失败
     */
    String register(String name, String email, String password, String license, String head, int type, String phone);

    /**
     * @param hash 激活所用哈希
     * @return 空或者null表示注册成功， 否则注册失败
     */
    String active(String email,String hash);
}

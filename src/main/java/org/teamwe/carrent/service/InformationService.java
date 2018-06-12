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
     * @return 用户实体类
     */
    User info(String email);
}

package org.teamwe.carrent.service;

import org.teamwe.carrent.entity.User;

/**
 * @author FDws
 * Created on 2018/6/12 9:16
 */

public interface EngineerService {
    /**
     * 获取所有未审核技师
     * @return 技师列表
     */
    User[] getEngineer();

    /**
     * 技师审核通过
     * @param email 标识技师
     * @return {@link org.teamwe.carrent.utils.ReturnStatus}
     */
    int check(String email);
}

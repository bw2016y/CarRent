package org.teamwe.carrent.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.teamwe.carrent.dao.UserDAO;
import org.teamwe.carrent.entity.User;
import org.teamwe.carrent.service.EngineerService;

import java.util.Iterator;
import java.util.List;

public class EngineerServiceImpl implements EngineerService {

    @Autowired
    private UserDAO userDAO;
    /**
     * 获取所有未审核技师
     *
     * @return 技师列表
     */
    @Override
    public User[] getEngineer() {

        List<User> users = userDAO.get_unchecked_engineers();

        User[] engineer = new User[users.size()];

        int i = 0;
        for (Iterator<User> iterator = users.iterator();iterator.hasNext();) {
            User user = iterator.next();
            engineer[i] = user;
            i++;

        }

        return engineer;
    }

    /**
     * 技师审核通过
     *
     * @param email 标识技师
     * @return {@link org.teamwe.carrent.utils.ReturnStatus}
     */
    @Override
    public int check(String email) {
        return 0;
    }
}

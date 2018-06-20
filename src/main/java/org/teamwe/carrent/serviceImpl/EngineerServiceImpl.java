package org.teamwe.carrent.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.teamwe.carrent.dao.UserDAO;
import org.teamwe.carrent.entity.User;
import org.teamwe.carrent.service.EngineerService;
import org.teamwe.carrent.utils.ReturnStatus;

import java.util.Iterator;
import java.util.List;

@Service
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
        System.out.println("技师数："+ users.size());

        int i = 0;
        for (Iterator<User> iterator = users.iterator();iterator.hasNext();) {
            User user = iterator.next();
            //System.out.println(user.toString());
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
        User engineer = userDAO.Get_userByEmial(email);
        engineer.setIsauthorized(1);
        int result = userDAO.Update_user(engineer);

        if(result < 0){
            System.out.println("技师激活失败");
            return ReturnStatus.FAILURE;
        }

        System.out.println("技师激活成功");
        return ReturnStatus.SUCCESS;
    }
}

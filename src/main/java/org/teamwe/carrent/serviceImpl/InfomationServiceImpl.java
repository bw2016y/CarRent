package org.teamwe.carrent.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.teamwe.carrent.dao.UserDAO;
import org.teamwe.carrent.entity.User;
import org.teamwe.carrent.service.InformationService;
import org.teamwe.carrent.utils.ReturnStatus;

public class InfomationServiceImpl implements InformationService {

    @Autowired
    private UserDAO userDAO;
    /**
     * 获取用户实体类
     *
     * @param email 用户登陆email
     * @return 用户实体类， null时表示请求失败
     */
    @Override
    public User info(String email) {
        User user ;
        user = userDAO.Get_userByEmial(email);

        if(user != null)
            return  user;

        return null;
    }
    /**
     * @param user 更新后的User
     * @return {@link org.teamwe.carrent.utils.ReturnStatus}
     */
    @Override
    public int changeInfo(User user) {
        int result = userDAO.Update_user(user);

        if(result >= 0)
            return ReturnStatus.SUCCESS;
        return ReturnStatus.FAILURE;
    }

    /**
     * 删除用户  逻辑删除，将用户的status改成1
     *
     * @param email 用户Email
     * @return {@link org.teamwe.carrent.utils.ReturnStatus}
     */
    @Override
    public int delete(String email) {

        User user = userDAO.Get_userByEmial(email);
        user.setStatus(1);

        int result = userDAO.Update_user(user);

        if(result >= 0)
            return ReturnStatus.SUCCESS;

        return ReturnStatus.FAILURE;
    }

    /**
     * 忘记密码之后使用邮箱重置， 此方法调用应该向email发送重置密码的链接
     *
     * @param email 用户email
     * @return 是否请求成功 {@link org.teamwe.carrent.utils.ReturnStatus}
     */
    @Override
    public int forgetPassword(String email) {
        return 0;
    }

    @Override
    public int resetPassword(String hash, String password) {
        return 0;
    }
}

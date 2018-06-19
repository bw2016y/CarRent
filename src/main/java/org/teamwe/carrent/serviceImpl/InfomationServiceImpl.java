package org.teamwe.carrent.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.teamwe.carrent.dao.UserDAO;
import org.teamwe.carrent.entity.User;
import org.teamwe.carrent.service.InformationService;
import org.teamwe.carrent.utils.ReturnStatus;
import org.teamwe.carrent.utils.hash.Hash;
import org.teamwe.carrent.utils.serviceUtil.EmailTest;
import org.teamwe.carrent.utils.serviceUtil.ResisTest;

public class InfomationServiceImpl implements InformationService {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private Hash hashh;
    @Autowired
    private ResisTest resisTest;
    /**
     * 获取用户实体类
     *
     * @param email 用户登陆email
     * @return 用户实体类， null时表示请求失败
     */
    @Override
    public User info(String email) {
        User user;
        user = userDAO.Get_userByEmial(email);
        System.out.println("通过email获得用户信息"+user.toString());

        return user;
    }
    /**
     * @param user 更新后的User
     * @return {@link org.teamwe.carrent.utils.ReturnStatus}
     */
    @Override
    public int changeInfo(User user) {

        int result = userDAO.Update_user(user);

        if(result >= 0)  {
            System.out.println("更新用户信息"+user.toString());
            return ReturnStatus.SUCCESS;
        }

        System.out.println("更新失败");
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

        if(result >= 0){
            System.out.println("逻辑删除用户成功！");
            return ReturnStatus.SUCCESS;
        }


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

        String random_string = hashh.genRandomChar(20); //随机生成hash值用来激活用户
        System.out.println("随机字符："+random_string);

        String hash_random_string = hashh.hashPassword(random_string);
        EmailTest.sendMail(email,"重置密码",random_string);//向用户发送重置密码邮件，包含随机字符

        resisTest.insertString(hash_random_string,email);//将随机字符的hash值存入redis中
        System.out.println("在redis中存储随机字符hash："+hash_random_string+"及用户email");

        return ReturnStatus.SUCCESS;
    }

    /**
     * 重置密码请求
     *
     * @param hash     发送到用户邮箱的序列码
     * @param password 用户重置的密码
     * @return 重置是否成功 {@link org.teamwe.carrent.utils.ReturnStatus}
     */
    @Override
    public int resetPassword(String hash, String password) {
        String hash_random = hashh.hashPassword(hash);

        if (!resisTest.isExist(hash_random))
        {
            System.out.println("redis中已经不存在此条记录，重置密码超时！");
            return ReturnStatus.FAILURE;//重置密码超时
        }


        String email = resisTest.getString(hash_random);

        User user = userDAO.Get_userByEmial(email);

        String hash_password = hashh.hashPassword(password);

        user.setPassword(hash_password);

        userDAO.Update_user(user);//更新用户密码
        System.out.println("更改密码成功");

        return ReturnStatus.SUCCESS;


    }
}

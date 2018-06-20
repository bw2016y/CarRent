package org.teamwe.carrent.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.teamwe.carrent.dao.UserDAO;
import org.teamwe.carrent.entity.User;
import org.teamwe.carrent.service.LoginService;
import org.teamwe.carrent.utils.hash.Hash;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private Hash hash;

    /**
     * @param email    用户登陆email
     * @param password 用户的密码, 未哈希过
     * @return 长度为1的数组， 表示登录失败， 存储的是失败信息
     * 长度为3 的数组表示登录成功， 第一个为用户名， 第二个为头像哈希码, 第三个Type
     */
    @Override
    public String[] login(String email, String password) {
        System.out.println(password);
        User user = null;
        user = userDAO.Get_userByEmial(email);
        System.out.println(user.toString());

        if(user == null||user.getStatus() == 1){//判断用户是否物理存在且逻辑存在,1表示逻辑删除
            String[] message1 = new String[1];
            message1[0] = "用户不存在，请检查邮箱是否正确！";
            System.out.println("用户不存在或已经被逻辑删除");
            return  message1;
        }

        String hash_password = user.getPassword();
        if(!hash_password.equals(hash.hashPassword(password, hash_password))){ //判断密码是否正确
            String[] message1 = new String[1];
            message1[0] = "密码错误！";
            System.out.println("密码错误！");
            return  message1;

        }

        String[] message2 = new String[3];
        message2[0] = user.getName();
        message2[1] = user.getHead();
        message2[2] = String.valueOf(user.getType());

        System.out.println("登录成功");
        System.out.println(message2[0]);


        return message2;
    }
}

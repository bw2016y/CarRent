package org.teamwe.carrent.dao;

import org.teamwe.carrent.entity.TempUser;
import org.teamwe.carrent.entity.User;

import java.util.Map;

public interface UserDAO {



    /**
     *     物理存在 (逻辑未知 需要继续判断 status字段)
     * @param email
     * @return    true (email 存在)  false(email 不存在)
     */
    //  boolean Check_Email_Phy(String email);



    /**
     *    物理 ，逻辑均存在
     * @param email
     * @return    true (email 存在)  false(email 不存在)
     */
   // boolean Check_Email_Logic(String email);


      /**
      *
      *  @param  user
      *  @return   true(注册成功) false(注册失败)
      */
       boolean Add_usr(User user);

    /**
     *
     * @param user
     * @return
     */
     boolean save_hash_user(TempUser user);

    /**
     *
     * @param email
     * @return   tempUser 实体类 对象
     */
     TempUser get_hash_user(String email);


    /**
     *
     *
     * @param email   激活成功，把用户从未激活的表里删除
     * @return     true(删除成功) false(删除失败)
     */
    boolean delete_temp_user(String email);

    /**
     *
     *
     * @param email  在用户表里 更改用户的激活状态
     * @return     true(更改成功) false(更改失败)
     */
    //boolean  active_user(String email);


    /**
     * 根据email 返回整个User 实体的全部信息
     *
     * @param email
     * @return
     */
    User Get_userByEmial(String email);


    /**
     *     获取用户的信息 并更新
     * @param user
     * @return   true 更新成功  false  更新失败
     */

    boolean Update_user (User user);


    /**
     *
     *    根据emial 逻辑删除一个用户信息
     * @param email
     * @return   true 逻辑删除成功  false 反之
     */
      boolean Delete_user(String email);











}

package org.teamwe.carrent.dao;

import org.teamwe.carrent.entity.TempUser;
import org.teamwe.carrent.entity.User;

import java.util.List;
import java.util.Map;

public interface UserDAO {






      /**
      *
      *  @param  user
      *  @return   true(注册成功) false(注册失败)
      */
      public    int Add_usr(User user);










      /**
     * 根据email 返回整个User 实体的全部信息
     *
     * @param email
     * @return
     */
      public  User Get_userByEmial(String email);


    /**
     *     获取用户的信息 并更新
     * @param user
     * @return   true 更新成功  false  更新失败
     */

    public int Update_user (User user);


    /**
     *
     *    根据emial 逻辑删除一个用户信息
     * @param email
     * @return   true 逻辑删除成功  false 反之
     */
     public int Delete_user (String email);

    /**
     *          返回用户列表
     * @return
     */
     public List<User> getUserlist();


    /**
     *  返回所有未审核的技师
     * @return
     */
     public  List<User> get_unchecked_engineers();






}

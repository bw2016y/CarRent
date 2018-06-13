package org.teamwe.carrent.dao;

import org.teamwe.carrent.entity.TempUser;

public interface TempUserDAO {

    /**
     *
     * @param user
     * @return
     */
    public  int  save_hash_user(TempUser user);



    /**
     *
     * @param email
     * @return   tempUser 实体类 对象
     */
    public TempUser get_hash_user(String email);



    /**
     *
     *
     * @param email   激活成功，把用户从未激活的表里删除
     * @return     true(删除成功) false(删除失败)
     */
    public  int delete_temp_user(String email);

}

package org.teamwe.carrent.dao;

import org.teamwe.carrent.entity.User;

public interface UserDAO {

     boolean Add_usr(User user);
     User Get_userByEmial(String email);
     boolean Update_user (User user);
     boolean Delete_user(String email);




}

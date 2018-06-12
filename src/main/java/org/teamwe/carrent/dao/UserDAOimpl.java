package org.teamwe.carrent.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.teamwe.carrent.entity.User;

import java.util.List;

@Repository
public class UserDAOimpl  implements  UserDAO {
    @Autowired
    private JdbcTemplate  jdbcTemplate;

    @Override
    public boolean Add_usr(User user) {
        return false;
    }

    @Override
    public User Get_userByEmial(String email) {
       List<User>  list=jdbcTemplate.query("select * from user where email=?",new Object[]{email},new BeanPropertyRowMapper<>(User.class));


        return  list.get(0);
    }

    @Override
    public boolean Update_user(User user) {
        return false;
    }

    @Override
    public boolean Delete_user(String email) {
        return false;
    }
}

package org.teamwe.carrent.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.teamwe.carrent.entity.User;
import org.teamwe.carrent.utils.DButils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDAOimpl  implements  UserDAO {

    @Override
    public int Add_usr(User user) {
        int return_val;
        Connection con=null;
        String email=user.getEmail();
        String password=user.getPassword();
        String name=user.getName();
        String licence=user.getLicence();
        String head=user.getHead();
        String phone=user.getPhone();

        int type=  user.getType();
        int credit=user.getCredit();
        int points=user.getPoints();
        int status=user.getStatus();
        int isvalidated=user.getIsvalidated();

        try{

            con=DButils.getConnection();
            String sql="insert  into  user(email,password,name,licence,head,phone,type,credit,points,status,isvalidated) " +
                    " values(?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,email);
            ps.setString(2,password);
            ps.setString(3,name);
            ps.setString(4,licence);
            ps.setString(5,head);
            ps.setString(6,phone);

            ps.setInt(7,type);
            ps.setInt(8,credit);
            ps.setInt(9,points);
            ps.setInt(10,status);
            ps.setInt(11,isvalidated);

            return_val=  ps.executeUpdate();
            return return_val;
        } catch (Exception e){
            e.printStackTrace();
            return  -1;
        }
        finally {
            if(con!=null){
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public User Get_userByEmial(String email) {
        return null;
    }

    @Override
    public int Update_user(User user) {
        return 0;
    }

    @Override
    public int Delete_user(String email) {
        return 0;
    }

    @Override
    public List<User> getUserlist() {
        return null;
    }

    @Override
    public List<User> get_unchecked_engineers() {
        return null;
    }
}
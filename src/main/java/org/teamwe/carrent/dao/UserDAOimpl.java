package org.teamwe.carrent.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
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

//@Repository
public class UserDAOimpl  implements  UserDAO {
     private SqlSessionFactory sqlSessionFactory;

    public SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

   // @Autowired
    public  UserDAOimpl(SqlSessionFactory fac){
        this.setSqlSessionFactory(fac);
    }


    //    @Override
//    public int Add_usr(User user) {
//        int return_val;
//        Connection con=null;
//        String email=user.getEmail();
//        String password=user.getPassword();
//        String name=user.getName();
//        String licence=user.getLicence();
//        String head=user.getHead();
//        String phone=user.getPhone();
//
//        int type=  user.getType();
//        int credit=user.getCredit();
//        int points=user.getPoints();
//        int status=user.getStatus();
//        int isvalidated=user.getIsvalidated();
//
//        try{
//
//            con=DButils.getConnection();
//            String sql="insert  into  user(email,password,name,licence,head,phone,type,credit,points,status,isvalidated) " +
//                    " values(?,?,?,?,?,?,?,?,?,?,?)";
//            PreparedStatement ps=con.prepareStatement(sql);
//            ps.setString(1,email);
//            ps.setString(2,password);
//            ps.setString(3,name);
//            ps.setString(4,licence);
//            ps.setString(5,head);
//            ps.setString(6,phone);
//
//            ps.setInt(7,type);
//            ps.setInt(8,credit);
//            ps.setInt(9,points);
//            ps.setInt(10,status);
//            ps.setInt(11,isvalidated);
//
//            return_val=  ps.executeUpdate();
//            return return_val;
//        } catch (Exception e){
//            e.printStackTrace();
//            return  -1;
//        }
//        finally {
//            if(con!=null){
//                try {
//                    con.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//    }
    @Override
    public int Add_usr(User user) {



        int val=-1;
        SqlSession sqlSession =sqlSessionFactory.openSession();
        try{
            val=sqlSession.insert("test.insertUser",user);
            sqlSession.commit();
            System.out.println(val);
        }catch (Exception c){
            c.printStackTrace();
        } finally {
            if(sqlSession!=null){
                sqlSession.close();
            }
        }
        return val;

    }
    @Override
    public User Get_userByEmial(String email) {
        User user=null;
        SqlSession sqlSession=sqlSessionFactory.openSession();
        try{
             user=sqlSession.selectOne("test.findUserByEmail",email);

            System.out.println(user);
        }catch (Exception c){
            c.printStackTrace();
        } finally {
            if(sqlSession!=null){
                sqlSession.close();
            }
        }
        return user;


    }

    @Override
    public int Update_user(User user) {
        int val = -1;
        SqlSession sqlSession=sqlSessionFactory.openSession();
        try{
            val=sqlSession.update("test.update_User",user);
            sqlSession.commit();
            System.out.println(user);

        }catch (Exception c){
            c.printStackTrace();
        } finally {
            if(sqlSession!=null){
                sqlSession.close();
            }
        }
        return val;
    }

    @Override
    public int Delete_user(String email) {
        int val = -1;
        SqlSession sqlSession=sqlSessionFactory.openSession();
        try{
             val=sqlSession.delete("test.deleteUserByEmail",email);
             sqlSession.commit();
             System.out.println(val);

        }catch (Exception c){
            c.printStackTrace();
        } finally {
            if(sqlSession!=null){
                sqlSession.close();
            }
        }
        return val;
    }

    @Override
    public List<User> getUserlist() {
        List<User> list=null;
        SqlSession sqlSession=sqlSessionFactory.openSession();
        try{
            list=sqlSession.selectList("test.findallUser");

            System.out.println(list.size());
            for(User u:list){
                System.out.println(u.toString());
            }
        }catch (Exception c){
            c.printStackTrace();
        } finally {
            if(sqlSession!=null){
                sqlSession.close();
            }
        }
        return list;

    }

    @Override
    public List<User> get_unchecked_engineers() {
        List<User> list=null;
        SqlSession sqlSession=sqlSessionFactory.openSession();
        try{
            list=sqlSession.selectList("test.find_unchecked_engineers");

            System.out.println(list.size());
            for(User u:list){
                System.out.println(u.toString());
            }
        }catch (Exception c){
            c.printStackTrace();
        } finally {
            if(sqlSession!=null){
                sqlSession.close();
            }
        }
        return list;
    }
}

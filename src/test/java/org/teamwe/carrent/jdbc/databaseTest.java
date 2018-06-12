package org.teamwe.carrent.jdbc;

import com.mysql.cj.protocol.Resultset;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.teamwe.carrent.dao.UserDAOimpl;
import org.teamwe.carrent.entity.User;
import org.teamwe.carrent.utils.DBut;
import org.teamwe.carrent.utils.DButils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@RunWith(SpringRunner.class)
@SpringBootTest
public class databaseTest {

    //@Autowired
    //DBut db;


    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    DataSourceProperties dataSourceProperties;
    @Test
    public void testJdbc(){

//         UserDAOimpl test=new UserDAOimpl();
//         User user=test.Get_userByEmial("1844002977@qq.com");
//         System.out.println(user.toString());

        DataSource dataSource=applicationContext.getBean(DataSource.class);
        System.out.println(dataSource);
        System.out.println(dataSource.getClass().getName());
        System.out.println(dataSourceProperties);
        System.out.println("finish");
    }

    @Test
    public  void test_con()throws  Exception{



        Connection con=DButils.getConnection();
        System.out.println(con);


        //  db.init();
        //  Connection con2=db.getConnection();
        //  System.out.println(con2);

        ResultSet rs;
        String sql="select * from user where email=?";
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setString(1,"1844002977@qq.com");
        rs=  ps.executeQuery();
        while(rs.next()){
            rs.getString("email");
            rs.getString("name");
            System.out.println( rs.getString("email"));
            System.out.println( rs.getString("name"));
        }






    }

    @Test
    public  void test_add()throws  Exception{

        UserDAOimpl udi= new UserDAOimpl();
        User user=new User("123.com","123","sddd","null","null","null",1,0,1,0,0);
        //  User user=new User("100.com","123" ,1,1,0);
        // User user=new User();
        int val= udi.Add_usr(user);
        System.out.println(val);

        System.out.println("hello");
    }
}
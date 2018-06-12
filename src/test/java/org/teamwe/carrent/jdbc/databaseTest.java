package org.teamwe.carrent.jdbc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.teamwe.carrent.dao.UserDAOimpl;
import org.teamwe.carrent.entity.User;
import org.teamwe.carrent.utils.DButils;

import javax.sql.DataSource;
import java.sql.Connection;

@RunWith(SpringRunner.class)
@SpringBootTest
public class databaseTest {

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
    }
}

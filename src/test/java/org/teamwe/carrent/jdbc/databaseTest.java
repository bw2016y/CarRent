package org.teamwe.carrent.jdbc;

import com.mysql.cj.protocol.Resultset;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.teamwe.carrent.dao.*;
import org.teamwe.carrent.entity.*;
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
    CarimgDAO ci;

   // @Autowired
  //  ApplicationContext applicationContext;
  //  @Autowired
   // DataSourceProperties dataSourceProperties;

//    @Test
//    public void testJdbc(){
//
//         UserDAOimpl test=new UserDAOimpl();
//         User user=test.Get_userByEmial("1844002977@qq.com");
//         System.out.println(user.toString());
//        DataSource dataSource=applicationContext.getBean(DataSource.class);
//        System.out.println(dataSource);
//        System.out.println(dataSource.getClass().getName());
//        System.out.println(dataSourceProperties);
//        System.out.println("finish");
//    }


    @Ignore
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

//    @Ignore
//    public  void test_add()throws  Exception{
//
//        UserDAOimpl udi= new UserDAOimpl();
//        User user=new User("123.com","123","sddd","null","null","null",1,0,1,0,0);
//        //  User user=new User("100.com","123" ,1,1,0);
//        // User user=new User();
//        int val= udi.Add_usr(user);
//        System.out.println(val);
//
//        System.out.println("hello");
//    }


    @Test
    public  void test_new()throws  Exception{

        UserDAOimpl ts= new UserDAOimpl(GetSqlSessionFactory.get_fac());
       User user=new User("new7.com","12345","sddd","null","null","null",1,0,1,0,0,0,1000);
       System.out.println(ts.Add_usr(user));



       //  UserDAO ts= new UserDAOimpl(GetSqlSessionFactory.get_fac());
        //  String email="1844002977@qq.com";
        // ts.Get_userByEmial(email);
      //    String email="100.com";
      //   ts.Delete_user(email);
      //     CarDAO cs= new CarDAOimpl(GetSqlSessionFactory.get_fac());

        //Car car=new Car("9999","benz","good",101,1,0,0,8);
        // cs.get_car_pages(2,0,null);
        // cs.get_car_pages(10,4,null);
        // cs.select_car_by_brand_Type_available("Audi",4);
        //cs.get_car_pages(10,0,"benz");
       //    int i=cs.get_car_pages(2,4,"benz");
      //    for(int c=1;c<=i;c++)
      //     cs.get_cars(c,2,4,"benz");

        //  cs.select_unchecked_car();
      //  cs.save_car(car);
       // System.out.println(cs.update_car(car));
       // Car car=cs.get_car("9999");
      //  if(car==null){
      //      System.out.println("there is null");
     //   }
      //  System.out.println(car.toString());
  //  OrderDAO od=new OrderDAOimpl(GetSqlSessionFactory.get_fac());
     //Order  order=new Order(2,"cc","8888",1,2,3,"nice",0);
//   Order order=new Order();
//   order.setOrderid(2);
//   order.setEmail("abcddd@qq.com");
//   order.setCard("8889");
//   order.setComment("nice");
//   order.setStatus(0);
//   order.setTimebegin(1);
//   order.setTimeende(2);
//   order.setTimeendr(3);
   //od.add_Order(order);
       // od.get_Orders_by_email("1844002977@qq.com");
      //  od.get_Orders_by_card("8888");
    //    od.update_order(order);
// od.add_Order(order);


//        CarBrandDAO cb= new CarBrandDAOimpl(GetSqlSessionFactory.get_fac());
//        CarBrand carBrand=new CarBrand("benz","xixi");
//        CarBrand carBrand2=new CarBrand("Audi","haha");
//        cb.add_brand(carBrand);
//        cb.add_brand(carBrand2);
//
//        cb.get_all_brand_img();
     //   CartypeDAO ct=new CartypeDAOimpl(GetSqlSessionFactory.get_fac());
//       CarType carType=new CarType(2,"niubicar");
//        CarType carType2=new CarType(4,"normal");
//        ct.add_type(carType);
//        ct.add_type(carType2);
       // ct.get_all_type();

       //CarimgDAO ci=new CarImgImpl(GetSqlSessionFactory.get_fac());
//         CarImg carImg=new CarImg();
//         carImg.setCard("9999");
//         carImg.setImg("ccc");
//         carImg.setImgid(1);
//        CarImg carImg2=new CarImg();
//        carImg2.setCard("8888");
//        carImg2.setImg("xixidemo2");
//        ci.sava_img(carImg);
//        ci.sava_img(carImg2);
      //  ci.get_cardimg_by_card("8888");



    }
}

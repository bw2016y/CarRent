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
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class databaseTest {


   @Autowired
   CarDAO carDAO;
   @Autowired
   UserDAO userDAO;
   @Autowired
   OrderDAO orderDAO;
   @Autowired
   CarBrandDAO carBrandDAO;
   @Autowired
   CarimgDAO carimgDAO;
   @Autowired
   CartypeDAO cartypeDAO;
   @Autowired
   CityDAO cityDAO;


   @Ignore
    public void testJdbc(){

//        UserDAOimpl test=new UserDAOimpl();
//        User user=test.Get_userByEmial("1844002977@qq.com");
//         System.out.println(user.toString());
//        DataSource dataSource=applicationContext.getBean(DataSource.class);
//        System.out.println(dataSource);
//        System.out.println(dataSource.getClass().getName());
//        System.out.println(dataSourceProperties);
//        System.out.println("finish");
    }


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
    public void test_UserDAO(){
    //  User user=new User("new9.com","12345","sddd","null","null","null",1,0,1,0,0,0,1000);
    //  userDAO.Add_usr(user);
     // userDAO.getUserlist();
    //  User newuser=new User("new9.com","124","sddd","null","null","null",1,0,1,0,0,0,1000);
   //   userDAO.Update_user(newuser);
        userDAO.get_unchecked_engineers();
    // userDAO.Get_userByEmial("new9.com");

    }

    @Test
    public void test_CarDAO(){


       // Car car=new Car("7422","benz","good",101,1,0,0,4,"new9.com","beijing");

     //   carDAO.save_car(car);

     //   carDAO.get_car("7419");

     //   carDAO.select_unchecked_car();
     //   Car car=new Car("7419","benz","well",101,1,0,0,8,"new9.com","beijing");
    //   carDAO.update_car(car);

        //    carDAO.select_car_by_brand_Type_available("benz",4);
 //      int i= carDAO.get_car_pages(2,4,"benz","beijing");

 //         for(int c=1;c<=i;c++)
  //            carDAO.get_cars(c,2,4,"benz","beijing");

    }


    @Test
    public void test_OrderDAO(){
       // Order order=new Order("123.com","9999",0,1,2,"bad",0,3,100);
       // orderDAO.add_Order(order);
       // orderDAO.get_order(3);
        //Order order=new Order(3,"123.com","9999",0,1,2,"bad",0,3,111);

        //orderDAO.update_order(order);

      //  orderDAO.get_Orders_by_email("123.com");
     //   orderDAO.get_Orders_by_card("9999");
       Order  order=  orderDAO.get_order(3);
      //order.get(0).getMoney();

        //System.out.println(order.get(0).getMoney());
        long m=order.getMoney();
        System.out.println(order.toString());
        System.out.println(m);


    }

    @Test
    public void test_CarBrandDAO(){


      //CarBrand carBrand=new CarBrand("cadirrac","xixi");
     //  carBrandDAO.add_brand(carBrand);

     carBrandDAO.get_all_brand_img();


    }

    @Test
    public void test_CarImgDAO(){

        // CarImg carImg=new CarImg("9999","bbb");
        //  carimgDAO.sava_img(carImg);
         carimgDAO.get_cardimg_by_card("9999");




    }
    @Test
    public void test_CarTypeDAO(){


     //CarType carType=new CarType(20,"bigcar");
     //cartypeDAO.add_type(carType);

      cartypeDAO.get_all_type();

    }
    @Test
    public void test_CityDAO(){
       // City city=new City("xian","somewhere");
      //  cityDAO.add_city(city);

       // cityDAO.get_city("xian");
      //  City city=new City("xian","哈哈");

        //   cityDAO.update_city(city);
         cityDAO.get_cities();
    }
}

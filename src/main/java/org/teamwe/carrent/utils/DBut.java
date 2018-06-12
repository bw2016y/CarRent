package org.teamwe.carrent.utils;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;


@Component
public class DBut {

    public         BasicDataSource  ds;

    @Value("${spring.datasource.driver-class-name}")
    private String driver_name;
    @Value("${spring.datasource.url}")
    private  String url ;
    @Value("${spring.datasource.username}")
    private  String username ;
    @Value("${spring.datasource.password}")
    private String password;
     @Value("${spring.datasource.dbcp2.max-wait-millis}")
     private String maxwaittime;
    @Value("${spring.datasource.dbcp2.max-total}")
    private  String maxtotal;



    public   void init(){
        ds = new  BasicDataSource();
        ds.setDriverClassName(driver_name);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setMaxWaitMillis(Long.valueOf(maxwaittime));
        ds.setMaxIdle(Integer.valueOf(maxtotal));
    }



    public Connection getConnection()throws  Exception{

   try{

       return  ds.getConnection();
   }
   catch (Exception e){
       throw  e ;

   }

    }



}

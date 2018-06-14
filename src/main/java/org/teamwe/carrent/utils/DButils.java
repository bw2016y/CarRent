package org.teamwe.carrent.utils;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;


import java.sql.Connection;
import java.util.Properties;

public class DButils {



    public static  BasicDataSource ds;


    //  @Value("${spring.datasource.driver-class-name}")
    private static String driver_name="com.mysql.cj.jdbc.Driver";
    //  @Value("${spring.datasource.url}")
    private static String url="jdbc:mysql://123.207.172.223:3306/car?useUnicode=true&characterEncoding=utf8";
    //  @Value("${spring.datasource.username}")
    private static String username="team";
    //  @Value("${spring.datasource.password}")
    private static String password="teampass";
    // @Value("${spring.datasource.dbcp2.max-wait-millis}")
    private static String maxwaittime="5000";
    //  @Value("${spring.datasource.dbcp2.max-total}")
    private static String maxtotal="10";




    static{

        ds=new BasicDataSource();
        ds.setDriverClassName(driver_name);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setMaxWaitMillis(Long.valueOf(maxwaittime));
        ds.setMaxIdle(Integer.valueOf(maxtotal));



    }


    public static Connection getConnection()throws Exception{

        try{
            return  ds.getConnection();
        }catch(Exception e){
            throw e;
        }
    }

}

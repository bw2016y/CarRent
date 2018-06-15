package org.teamwe.carrent.dao;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;

@Repository
public class GetSqlSessionFactory {
    private  static   SqlSessionFactory sqlSessionFactory;

//    public GetSqlSessionFactory() {
//        String resource="SqlMapConfig.xml";
//        InputStream inputStream= null;
//        try {
//            inputStream = Resources.getResourceAsStream(resource);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        sqlSessionFactory = new SqlSessionFactoryBuilder()
//                .build(inputStream);
//
//    }

    static{
        String resource="SqlMapConfig.xml";
        InputStream inputStream= null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder()
                .build(inputStream);
    }

    public  static  SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    public  static    void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        GetSqlSessionFactory.sqlSessionFactory = sqlSessionFactory;
    }

    public  static  SqlSessionFactory get_fac(){
        return  sqlSessionFactory;
     }


}

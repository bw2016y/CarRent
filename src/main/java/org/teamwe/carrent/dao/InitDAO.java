package org.teamwe.carrent.dao;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class InitDAO {
  @Bean
  public SqlSessionFactory get_fac(){
      String resource="SqlMapConfig.xml";
      InputStream inputStream= null;
      try {
          inputStream = Resources.getResourceAsStream(resource);
      } catch (IOException e) {
          e.printStackTrace();
      }
       return  new SqlSessionFactoryBuilder().build(inputStream);
  }

}

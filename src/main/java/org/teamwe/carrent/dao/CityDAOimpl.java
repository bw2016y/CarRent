package org.teamwe.carrent.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;
import org.teamwe.carrent.entity.CarType;
import org.teamwe.carrent.entity.City;
import org.teamwe.carrent.entity.User;

import java.util.List;
@Repository
public class CityDAOimpl implements  CityDAO {
    private SqlSessionFactory sqlSessionFactory;

    public SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public CityDAOimpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public List<City> get_cities() {
        List<City> list=null;
        SqlSession sqlSession=sqlSessionFactory.openSession();
        try{
            list=sqlSession.selectList("test.select_all_city");

            System.out.println(list.size());
            for(City u:list){
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
    public int add_city(City ct) {

        int val=-1;
        SqlSession sqlSession =sqlSessionFactory.openSession();
        try{
            val=sqlSession.insert("test.insert_city",ct);
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
    public int update_city(City city) {

        int val = -1;
        SqlSession sqlSession=sqlSessionFactory.openSession();
        try{
            val=sqlSession.update("test.update_city",city);
            sqlSession.commit();
            System.out.println(city.toString());

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
    public City get_city(String city) {

        City city1 = null;
        SqlSession sqlSession=sqlSessionFactory.openSession();
        try{
            city1=sqlSession.selectOne("test.select_city_by_cityname",city);

            System.out.println(city1.toString());
        }catch (Exception c){
            c.printStackTrace();
        } finally {
            if(sqlSession!=null){
                sqlSession.close();
            }
        }
        return city1;

    }
}

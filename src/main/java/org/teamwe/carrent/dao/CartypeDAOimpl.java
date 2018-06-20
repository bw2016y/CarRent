package org.teamwe.carrent.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;
import org.teamwe.carrent.entity.CarBrand;
import org.teamwe.carrent.entity.CarType;

import java.util.List;
@Repository
public class CartypeDAOimpl  implements  CartypeDAO {
    private SqlSessionFactory sqlSessionFactory;

    public CartypeDAOimpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public List<CarType> get_all_type() {
        List<CarType> list=null;
        SqlSession sqlSession=sqlSessionFactory.openSession();
        try{
            list=sqlSession.selectList("test.select_all_type");

            System.out.println(list.size());
            for(CarType u:list){
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
    public int add_type(CarType carType) {
        int val=-1;
        SqlSession sqlSession =sqlSessionFactory.openSession();
        try{
            val=sqlSession.insert("test.insert_type",carType);
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
}

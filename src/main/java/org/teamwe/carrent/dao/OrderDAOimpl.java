package org.teamwe.carrent.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.teamwe.carrent.entity.Car;
import org.teamwe.carrent.entity.Order;

import java.util.List;

public class OrderDAOimpl implements  OrderDAO {
    private SqlSessionFactory sqlSessionFactory;

    public SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public OrderDAOimpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public int add_Order(Order order) {
        int val=-1;
        SqlSession sqlSession =sqlSessionFactory.openSession();
        try{
            val=sqlSession.insert("test.insertOrder",order);
            sqlSession.commit();
            System.out.println(val);
            System.out.println(order);
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
    public List<Order> get_Orders_by_email(String email) {

        List<Order> list=null;
        SqlSession sqlSession=sqlSessionFactory.openSession();
        try{
            list=sqlSession.selectList("test.select_order_email",email);

            System.out.println(list.size());
            for(Order u:list){
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
    public Order get_order(int orderid) {
       Order order = null;
        SqlSession sqlSession=sqlSessionFactory.openSession();
        try{
            order=sqlSession.selectOne("test.select_order_by_id",orderid);

            System.out.println(order);

        }catch (Exception c){
            c.printStackTrace();
        } finally {
            if(sqlSession!=null){
                sqlSession.close();
            }
        }
        return order;
    }

    @Override
    public int update_order(Order order) {
        int val = -1;
        SqlSession sqlSession=sqlSessionFactory.openSession();
        try{
            val=sqlSession.update("test.update_order",order);
            sqlSession.commit();
            System.out.println(order);
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
    public List<Order> get_Orders_by_card(String card) {
        List<Order> list=null;
        SqlSession sqlSession=sqlSessionFactory.openSession();
        try{
            list=sqlSession.selectList("test.select_order_by_card",card);

            System.out.println(list.size());
            for(Order u:list){
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
}

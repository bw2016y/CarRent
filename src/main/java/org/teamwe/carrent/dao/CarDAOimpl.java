package org.teamwe.carrent.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;
import org.teamwe.carrent.entity.Car;
import org.teamwe.carrent.entity.TempCar;
import org.teamwe.carrent.entity.User;

import java.util.List;
@Repository
public class CarDAOimpl implements CarDAO{
    private SqlSessionFactory sqlSessionFactory;

    public SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }
    public  CarDAOimpl(SqlSessionFactory fac){
        this.setSqlSessionFactory(fac);
    }
    @Override
    public int save_car(Car car) {


        int val=-1;
        SqlSession sqlSession =sqlSessionFactory.openSession();
        try{
            val=sqlSession.insert("test.insertCar",car);
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
    public List<Car> select_car_by_brand_Type_available(String brand, int type) {
        Car car =new Car();
        car.setBrand(brand);
        car.setType(type);
        List<Car> list=null;
        SqlSession sqlSession=sqlSessionFactory.openSession();
        try{
            list=sqlSession.selectList("test.select_car_by_brand_type",car);

            System.out.println(list.size());
            for(Car u:list){
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
    public List<Car> select_unchecked_car() {


        List<Car> list=null;
        SqlSession sqlSession=sqlSessionFactory.openSession();
        try{
            list=sqlSession.selectList("test.select_unchecked_car" );

            System.out.println(list.size());
            for(Car u:list){
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
    public Car get_car(String card) {
       Car car = null;
        SqlSession sqlSession=sqlSessionFactory.openSession();
        try{
            car=sqlSession.selectOne("test.get_car_by_card",card );

            System.out.println(car);

        }catch (Exception c){
            c.printStackTrace();
        } finally {
            if(sqlSession!=null){
                sqlSession.close();
            }
        }
        return car;



    }

    @Override
    public int update_car(Car car) {
        int val = -1;
        SqlSession sqlSession=sqlSessionFactory.openSession();
        try{
            val=sqlSession.update("test.update_car",car);
            sqlSession.commit();
            System.out.println(car);

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
    public int get_car_pages(int length, int type, String brand,String city) {
        TempCar tc=new TempCar();
        tc.setLength(length);
        tc.setType(type);
        tc.setBrand(brand);
        tc.setCity(city);
        int cnt=-1;
        int page;
        SqlSession sqlSession=sqlSessionFactory.openSession();
        try{
            cnt=sqlSession.selectOne("test.get_car_pages",tc);

            System.out.println("记录数"+cnt);

        }catch (Exception c){
            c.printStackTrace();
        } finally {
            if(sqlSession!=null){
                sqlSession.close();
            }
        }
        page=(int)Math.ceil(1.0*cnt/length);
        System.out.println("页数"+page);
        return page;
    }

    @Override
    public List<Car> get_cars(int page, int length, int type, String brand,String city) {
        List<Car> list=null;
        TempCar tc= new TempCar();
        tc.setBrand(brand);
        tc.setType(type);
        tc.setLength(length);
        int pg=(page-1)*length;
        tc.setPage(pg);
        tc.setCity(city);



        SqlSession sqlSession=sqlSessionFactory.openSession();
        try{
            list=sqlSession.selectList("test.get_cars_by_pages_type_brand",tc);

            System.out.println("该页记录数"+list.size());
            for(Car u:list){
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
    public Car get_car_by_card(String card) {
        Car car=null;
        SqlSession sqlSession=sqlSessionFactory.openSession();
        try{
            car=sqlSession.selectOne("test.findCarByCard",card);

            System.out.println(car);
        }catch (Exception c){
            c.printStackTrace();
        } finally {
            if(sqlSession!=null){
                sqlSession.close();
            }
        }
        return car;
    }
}

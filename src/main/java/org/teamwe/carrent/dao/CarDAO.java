package org.teamwe.carrent.dao;

import org.teamwe.carrent.entity.Car;

import java.util.List;

public interface   CarDAO {

    /**
     *
     * @param car  保存车辆信息
     * @return
     */
    public  int save_car(Car car);



    /**
     *   根据品牌和类型查询可用车辆
     *
      * @param brand
     * @param type
     * @return
     */
    public List<Car>  select_car_by_brand_Type_available(String brand,int type);

    /**
     *  返回所有待检查车辆
     * @return
     */
    public List<Car>  select_unchecked_car();

    /**
     * 根据车牌返回车辆所有信息
     * @param card
     * @return
     */
    public Car get_car(String  card);

    /**
     * 更新车辆信息
     *
     * @param car
     * @return
     */
    public int update_car(Car car);

    /**
     *   返回总的页数
     * @param length  每页的记录数
     * @param type     车类型
     * @param brand     车品牌
     * @param city      所在的城市
     * @return
     */
    public int get_car_pages(int length ,int type ,String brand, String city);


    /**
     *  返回某一页的所有记录
     *
     * @param page     第几页
     * @param length   每页的长度
     * @param type
     * @param brand   same with  former
     * @param city
     * @return
     */
    public List<Car> get_cars(int page,int length,int type,String brand,String city);


    /**
     * 根据车牌card
     * 如果存在则返回对应Car对象
     *
     * @param card
     * @return
     */
    public Car get_car_by_card(String card);



}

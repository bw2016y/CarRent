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
     *    返回总页数 (全部可用)
     * @param length    每页的长度
     * @param type      车的类型
     * @param brand     车的品牌
     * @return
     */
    public int get_car_pages(int length ,int type ,String brand);


    /**
     *  返回car 的列表 (全部可用)
     *
     * @param page      页数
     * @param length    每页的长度
     * @param type      车的类型        0  查询所有
     * @param brand     车的品牌       null 查询所有品牌
     * @return
     */
    public List<Car> get_cars(int page,int length,int type,String brand);







}

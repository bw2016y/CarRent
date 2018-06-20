package org.teamwe.carrent.service;

import org.teamwe.carrent.entity.Car;

import java.util.List;

/**
 * @author FDws
 * Created on 2018/6/12 9:28
 */

public interface CarService {


    /**
     * 根据card值 返回所有图片hash
     *
     * @param card Car id
     * @return image array
     */
    String[] get_car_imgs(String card);


    /**
     * 用户上传车辆信息
     *
     * @param email   用户email
     * @param type    车辆类型
     * @param card    车牌
     * @param brand   车品牌
     * @param hash    车辆图片哈希列表
     * @param message 车辆简介
     * @param price   车辆价格
     * @return 成功或者失败 {@link org.teamwe.carrent.utils.ReturnStatus}
     */
    int addCar(String email, int type, String card, String brand, String[] hash, String message, int price, String city);

    /**
     * 获取所有类型
     *
     * @return 类型列表
     */
    List<Integer> getType();

    /**
     * 添加类型
     *
     * @param number 座位数
     * @return {@link org.teamwe.carrent.utils.ReturnStatus}
     */
    int addType(int number);

    /**
     * 获取所有品牌
     *
     * @return 品牌列表， 包括名称和logo哈希码
     */
    List<String[]> getBrand();

    /**
     * 管理员新建品牌
     *
     * @param name 品牌名称
     * @param hash 品牌logo哈希
     * @return {@link org.teamwe.carrent.utils.ReturnStatus}
     */
    int addBrand(String name, String hash);

    /**
     * @return 返回待审核车辆的列表
     */
    List<Car> getCheckCar();

    /**
     * @param card 车牌号
     * @return {@link org.teamwe.carrent.utils.ReturnStatus}
     */
    int checkCar(String card);

    /**
     * @param begin  开始的页数
     * @param length 每页的长度
     * @param type   车辆类型
     * @param brand  车辆品牌
     * @return Car对象的列表
     */
    List<Car> getCars(int begin, int length, int type, String brand, String city);

    /**
     * @param type   Car type
     * @param brand  Car brand
     * @param length Length of one page
     * @return ALl of car pages
     */
    int carPages(int type, String brand, int length, String city);
}

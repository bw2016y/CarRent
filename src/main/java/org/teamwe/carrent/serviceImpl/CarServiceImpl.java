package org.teamwe.carrent.serviceImpl;

import org.teamwe.carrent.entity.Car;
import org.teamwe.carrent.service.CarService;

import java.util.List;

public class CarServiceImpl implements CarService {


    /**
     *   根据card值 返回所有图片hash
     * @param card
     * @return
     */
    @Override
    public String[] get_car_imgs(String card) {



        return new String[0];
    }

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

    @Override
    public int addCar(String email, int type, String card, String brand, String[] hash, String message, int price) {
        return 0;
    }

    @Override
    public List<Integer> getType() {
        return null;
    }

    @Override
    public int addType(int number) {
        return 0;
    }

    @Override
    public List<String[]> getBrand() {
        return null;
    }

    @Override
    public int addBrand(String name, String hash) {
        return 0;
    }

    @Override
    public List<Car> getCheckCar() {
        return null;
    }

    @Override
    public int checkCar(String card) {
        return 0;
    }

    @Override
    public List<Car> getCars(int begin, int length, int type, String brand) {
        return null;
    }

    @Override
    public int carPages(int type, String brand, int length) {
        return 0;
    }
}

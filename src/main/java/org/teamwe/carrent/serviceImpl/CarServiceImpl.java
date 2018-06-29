package org.teamwe.carrent.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.teamwe.carrent.dao.CarBrandDAO;
import org.teamwe.carrent.dao.CarDAO;
import org.teamwe.carrent.dao.CarimgDAO;
import org.teamwe.carrent.dao.CartypeDAO;
import org.teamwe.carrent.entity.Car;
import org.teamwe.carrent.entity.CarBrand;
import org.teamwe.carrent.entity.CarImg;
import org.teamwe.carrent.entity.CarType;
import org.teamwe.carrent.service.CarService;
import org.teamwe.carrent.utils.ReturnStatus;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    /**
     * 1 card      varchar     主键      非空
     * 2 brand     varchar     非空
     * 3 message       varchar
     * 4 price         int      非空
     * 5 ischecked     int      非空 0未检查 1已检查
     * 6 available     int     非空 0 可用  1不可用
     * 7 status        int     非空 0逻辑存在 1逻辑删除
     * 8 type          int     非空  表示汽车的可乘坐人数
     */


    private  static  int ischecked  = 0;//用户上传车辆时车辆未审核
    private static int available = 1;//用户上传车辆时车辆默认不可用
    private static  int status = 0;//用户上传车辆默认逻辑存在

    @Autowired
    private CarimgDAO carimgDAO;
    @Autowired
    private CarDAO carDAO;
    @Autowired
    private CartypeDAO cartypeDAO;
    @Autowired
    private CarBrandDAO carBrandDAO;
    /**
     *   根据card值 返回所有图片hash
     * @param card
     * @return
     */
    @Override
    public String[] get_car_imgs(String card) {
        List<CarImg> carImgs = carimgDAO.get_cardimg_by_card(card);

        String[] carImages = new String[carImgs.size()];

        int i = 0;
        for(Iterator<CarImg> carImgIterator = carImgs.iterator();carImgIterator.hasNext(); ){

            CarImg carImg = carImgIterator.next();
            carImages[i] = carImg.getImg();
            System.out.println(carImages[i]);
            i++;

        }
        return carImages;
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
    public int addCar(String email, int type, String card, String brand, String[] hash, String message, int price,String city) {

        if(carDAO.get_car(card)!=null){
            System.out.println("该车辆已经被上传");
            return ReturnStatus.FAILURE;
        }

        Car car = new Car(card,brand,message,price,ischecked,available,status,type,email,city);//上传车辆信息
        if(carDAO.save_car(car) < 0){ //保存车辆信息
            return ReturnStatus.FAILURE;
        }
        System.out.println("保存车辆信息："+car.toString());

        for (int i = 0;i < hash.length;i++){
            CarImg carImg = new CarImg(0,card,hash[i]);//逐张图片上传
            carimgDAO.sava_img(carImg);
        }

        System.out.println("保存车辆图片成功");
        return ReturnStatus.SUCCESS;
    }
    /**
     * 获取所有类型
     *
     * @return 类型列表
     */
    @Override
    public List<Integer> getType() {
        List<CarType> carTypes = cartypeDAO.get_all_type();
        List<Integer> types = new ArrayList<Integer>();

        for(Iterator<CarType> carTypeIterator = carTypes.iterator();carTypeIterator.hasNext();){
            CarType carType = carTypeIterator.next();

            types.add(carType.getType());
        }
        System.out.println("共有车型："+ types.size());

        return types;
    }

    /**
     * 添加类型
     *
     * @param number 座位数
     * @return {@link org.teamwe.carrent.utils.ReturnStatus}
     */
    @Override
    public int addType(int number) {
        String decr = "这是"+number+"座车";
        CarType carType = new CarType(number,decr);


        if(cartypeDAO.add_type(carType) < 0){//添加一个新的座位数
            return ReturnStatus.FAILURE;
        }

        System.out.println("添加一个新的座位数"+number);

        return ReturnStatus.SUCCESS;
    }

    /**
     * 获取所有品牌
     *
     * @return 品牌列表， 包括名称和logo哈希码
     */
    @Override
    public List<String[]> getBrand() {

        List<CarBrand> carBrands = carBrandDAO.get_all_brand_img();

        List<String[]> brands = new ArrayList<String[]>();

        for (Iterator<CarBrand> carBrandIterator = carBrands.iterator();carBrandIterator.hasNext();){
            CarBrand carBrand = carBrandIterator.next();
            String[] strings = new String[2];
            strings[0] = carBrand.getBrand();
            strings[1] = carBrand.getImg();

            brands.add(strings);
        }

        System.out.println("获得所有品牌信息"+brands.toString());

        return brands;
    }

    /**
     * 管理员新建品牌
     *
     * @param name 品牌名称
     * @param hash 品牌logo哈希
     * @return {@link org.teamwe.carrent.utils.ReturnStatus}
     */
    @Override
    public int addBrand(String name, String hash) {

        CarBrand carBrand = new CarBrand(name,hash);
        if (carBrandDAO.add_brand(carBrand) < 0){
            return ReturnStatus.FAILURE;
        }
        System.out.println("插入新的品牌成功"+carBrand.toString());

        return ReturnStatus.SUCCESS;
    }

    /**
     * @return 返回待审核车辆的列表
     */
    @Override
    public List<Car> getCheckCar() {
        List<Car> cars = carDAO.select_unchecked_car();
        System.out.println("获得未审核车辆列表："+cars.toString());
        return cars;
    }

    /**
     * @param card 车牌号
     * @return {@link org.teamwe.carrent.utils.ReturnStatus}
     */
    @Override
    public int checkCar(String card) {//审核车辆通过
        Car car = carDAO.get_car(card);
        car.setIschecked(1);
        car.setAvailable(0);

        if(carDAO.update_car(car) < 0){
            return ReturnStatus.FAILURE;
        }

        System.out.println("车辆审核通过，车牌号："+card);

        return ReturnStatus.SUCCESS;
    }


    /**
     * @param type   Car type
     * @param brand  Car brand
     * @param length Length of one page
     * @return ALl of car pages
     */
    @Override
    public int carPages(int type, String brand, int length, String city) {
        int count = carDAO.get_car_pages(length,type,brand,city);
        return count;
    }

    /**
     * @param begin  开始的页数
     * @param length 每页的长度
     * @param type   车辆类型
     * @param brand  车辆品牌
     * @return Car对象的列表
     */
    @Override
    public List<Car> getCars(int begin, int length, int type, String brand, String city) {
        List<Car> newlist = new ArrayList<Car>(length);
        newlist = carDAO.get_cars(begin,length,type,brand,city);

        return newlist;
    }


}

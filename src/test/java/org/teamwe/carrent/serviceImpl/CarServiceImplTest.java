package org.teamwe.carrent.serviceImpl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.teamwe.carrent.entity.Car;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class CarServiceImplTest {
    @Autowired
    private  CarServiceImpl carService;

    @Test
    public void get_car_imgs() {
        carService.get_car_imgs("8888");
    }

    @Test
    public void addCar() {
        String[] carimages = {"image1","image2"};

        carService.addCar("155371667@qq.com",4,"陕V123456","benz",carimages,"豪车",100,"北京");
    }

    @Test
    public void getType() {
        carService.getType();
    }

    @Test
    public void addType() {
        carService.addType(10);
    }

    @Test
    public void getBrand() {
        List<String[]> brands = carService.getBrand();
        for (Iterator<String[]> iterator = brands.iterator();iterator.hasNext();){
            String[] s = iterator.next();
            for (int i = 0; i < s.length; i++) {
                System.out.println(s[i]);

            }
        }
    }

    @Test
    public void addBrand() {
        carService.addBrand("大众","dazhong");
    }

    @Test
    public void getCheckCar() {
        List<Car> cars = carService.getCheckCar();
        for(Iterator<Car> carIterator = cars.iterator();carIterator.hasNext();){
            Car car = carIterator.next();
            System.out.println(car.getCard());
        }
    }

    @Test
    public void checkCar() {
        carService.checkCar("陕V123456");
    }

    @Test
    public void carPages() {
        System.out.println(carService.carPages(0,null,3,null));
    }

    @Test
    public void getCars() {
        List<Car> cars = carService.getCars(1,3,0,"dazhong",null);
        for(Iterator<Car> carIterator = cars.iterator();carIterator.hasNext();){
            Car car = carIterator.next();
            System.out.println(car.getCard());
        }
    }
}
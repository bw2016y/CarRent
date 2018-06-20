package org.teamwe.carrent.serviceImpl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.teamwe.carrent.entity.City;

import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class CityServiceImplTest {

    @Autowired
    private CityServiceImpl cityService;

    @Test
    public void getCity() {
        List<City> cities = cityService.getCity();

        for (Iterator<City> iterator = cities.iterator();iterator.hasNext();){
            City city = iterator.next();
            System.out.println(city.toString());
        }


    }

    @Test
    public void addCity() {
        cityService.addCity("杨凌","西北农林科技大学北校区信息工程学院机房");
    }

    @Test
    public void updateCity() {
        cityService.updateCity("杨凌","西北农林科技大学北校区信息工程学院机房三楼");
    }
}
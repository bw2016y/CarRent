package org.teamwe.carrent.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.teamwe.carrent.dao.CityDAO;
import org.teamwe.carrent.entity.City;
import org.teamwe.carrent.service.CityService;
import org.teamwe.carrent.utils.ReturnStatus;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityDAO cityDAO;

    /**
     * 获取所有的支持城市列表
     *
     * @return 城市列表
     */
    @Override
    public List<City> getCity() {

        List<City> cities = cityDAO.get_cities();
        System.out.println("读取的城市列表"+cities.toString());
        return cities;
    }

    /**
     * 增加城市
     *
     * @param name     城市名
     * @param location 城市具体地点
     * @return 成功与否
     */

    @Override
    public int addCity(String name, String location) {


        if(cityDAO.get_city(name) != null){
            System.out.println("该城市已经存在！");
            return ReturnStatus.FAILURE;
        }

        City city = new City(name,location);
        int result = cityDAO.add_city(city);

        if(result < 0){
            System.out.println("插入城市失败！");
            return ReturnStatus.FAILURE;
        }

        System.out.println("插入城市成功");
        return ReturnStatus.SUCCESS;

    }

    @Override
    public int updateCity(String name, String newLoc) {

        City city = cityDAO.get_city(name);
        city.setSite(newLoc);

        int result = cityDAO.update_city(city);
        if(result < 0 ){
            System.out.println("更新城市地址失败");
            return  ReturnStatus.FAILURE;
        }

        System.out.println("更新城市地址成功");
        return ReturnStatus.SUCCESS;
    }
}

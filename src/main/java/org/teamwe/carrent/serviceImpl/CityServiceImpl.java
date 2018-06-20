package org.teamwe.carrent.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.teamwe.carrent.dao.CityDAO;
import org.teamwe.carrent.entity.City;
import org.teamwe.carrent.service.CityService;

import java.util.List;


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
        return 0;
    }

    @Override
    public int updateCity(String name, String newLoc) {
        return 0;
    }
}

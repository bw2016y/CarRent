package org.teamwe.carrent.service;

import org.teamwe.carrent.entity.City;

import java.util.List;

/**
 * @author FDws
 * Created on 2018/6/19 16:31
 */

public interface CityService {
    /**
     * 获取所有的支持城市列表
     *
     * @return 城市列表
     */
    List<City> getCity();

    /**
     * 增加城市
     *
     * @param name     城市名
     * @param location 城市具体地点
     * @return 成功与否
     */
    int addCity(String name, String location);

    /**
     * 更改城市的具体地点
     *
     * @param name   城市名
     * @param newLoc 新地点
     * @return 成功与否
     */
    int updateCity(String name, String newLoc);
}

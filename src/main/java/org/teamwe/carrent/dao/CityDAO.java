package org.teamwe.carrent.dao;

import org.teamwe.carrent.entity.City;

import java.util.List;

public interface CityDAO {

    /**
     *
     * 获取所有的城市
     * @return
     */
    List<City> get_cities();


    /**
     *  添加一个城市
     *
     * @param ct
     * @return
     */
    int  add_city(City ct);


    /**
     * 更新一个城市
     * @param city
     * @return
     */
    int update_city(City city);


    /**
     * 根据城市名称来获取城市
     *
     * @param city
     * @return
     */
    City get_city(String city);

}

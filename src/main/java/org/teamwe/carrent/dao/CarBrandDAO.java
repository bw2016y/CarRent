package org.teamwe.carrent.dao;

import org.teamwe.carrent.entity.CarBrand;

import java.util.List;

public interface CarBrandDAO {
    /**
     * 返回所有的车辆品牌和图片信息哈希码
     * @return
     */
    public List<CarBrand>  get_all_brand_img();

    /**
     * 插入一条品牌和图片记录
     * @param carBrand
     * @return
     */
    public int add_brand(CarBrand carBrand);


}

package org.teamwe.carrent.dao;

import org.springframework.stereotype.Repository;
import org.teamwe.carrent.entity.CarImg;

import java.util.List;
//@Repository
public interface CarimgDAO {


    /**
     *        插入一条carImg记录
     * @return
     */
    public int sava_img(CarImg carImg);


    /**
     *  根据一个card获取所有该车辆img hash
     * @param card
     * @return
     */
    public List<CarImg>  get_cardimg_by_card(String card);


    

}

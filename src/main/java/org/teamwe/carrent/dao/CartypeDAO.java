package org.teamwe.carrent.dao;

import org.teamwe.carrent.entity.CarType;

import java.util.List;

public interface CartypeDAO {
    /**
     *  返回所有车辆类型
     * @return
     */
      public List<CarType> get_all_type();

    /**
     *  增加一条car类型记录
     * @param carType
     * @return
     */
      public int add_type(CarType carType);
}

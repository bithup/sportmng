package com.xgh.mng.dao.write;

import com.xgh.mng.entity.Order;

/**
 * Created by CQ on 2016/11/7.
 */
public interface IOrderDaoW {

    public int insert(Order order);

    public int delete(long id);

    public int update(Order order);
}

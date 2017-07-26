package com.xgh.mng.dao;

import com.xgh.mng.dao.read.IOrderDaoR;
import com.xgh.mng.dao.write.IOrderDaoW;
import com.xgh.mng.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by CQ on 2016/11/7.
 */
@Service("orderDao")
public class OrderDaoImpl implements IOrderDao {

    @Autowired
    protected IOrderDaoR orderDaoR;

    @Autowired
    protected IOrderDaoW orderDaoW;


    public Order get(long id) {
        return orderDaoR.get(id);
    }

    public List<Map<String, Object>> getListPage(Map<String, Object> map) {
        return orderDaoR.getListPage(map);
    }

    public int getRows(Map<String, Object> map) {
        return orderDaoR.getRows(map);
    }

    public int insert(Order order) {
        return orderDaoW.insert(order);
    }

    public int delete(long id) {
        return orderDaoW.delete(id);
    }

    public int update(Order order) {
        return orderDaoW.update(order);
    }
}

package com.xgh.mng.dao.read;

import com.xgh.mng.entity.Order;

import java.util.List;
import java.util.Map;

/**
 * Created by CQ on 2016/11/7.
 */
public interface IOrderDaoR {

    public Order get(long id);

    public List<Map<String,Object>> getListPage(Map<String,Object> map);

    public int getRows(Map<String,Object> map);
}

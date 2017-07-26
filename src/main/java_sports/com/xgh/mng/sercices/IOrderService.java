package com.xgh.mng.sercices;

import com.xgh.mng.entity.Order;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/8.
 */
public interface IOrderService {

    public Order get(long id);

    public Map<String,Object> getGridList(HttpServletRequest request);

    public int delete(long id);

    public int update(Order order);

}

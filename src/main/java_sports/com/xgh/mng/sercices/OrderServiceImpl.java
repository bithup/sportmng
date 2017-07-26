package com.xgh.mng.sercices;

import com.xgh.mng.basic.BaseService;
import com.xgh.mng.dao.IOrderDao;
import com.xgh.mng.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/8.
 */
@Service("orderService")
public class OrderServiceImpl extends BaseService implements IOrderService {

    @Autowired
    protected IOrderDao orderDao;

    public Order get(long id) {
        return orderDao.get(id);
    }

    public Map<String, Object> getGridList(HttpServletRequest request) {

        Map<String,Object> gridMap = new HashMap<String, Object>();
        Map<String,Object> map = new HashMap<String, Object>();
        String page = request.getParameter("page");
        String pagesize = request.getParameter("pagesize");
        String account = request.getParameter("account");
        String orderNo = request.getParameter("orderNo");
        String orderStatus = request.getParameter("orderStatus");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        long unitId = getCurrentUnitId(request);
        map.put("page",Integer.parseInt(page));
        map.put("pagesize",Integer.parseInt(pagesize));
        map.put("account",account);
        map.put("orderNo",orderNo);
        map.put("orderStatus",orderStatus);
        map.put("unitId",unitId);
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        List<Map<String,Object>> list = orderDao.getListPage(map);
        for(Map<String,Object> orderMap:list){
            if(null!=String.valueOf(orderMap.get("orderStatus"))&&!"".equals(String.valueOf(orderMap.get("orderStatus")))){
                int status = Integer.parseInt(String.valueOf(orderMap.get("orderStatus")));
                if(status==0){
                    orderMap.put("orderStatus","待支付");
                }else if(status==1){
                    orderMap.put("orderStatus","待开始");
                }else if(status==2){
                    orderMap.put("orderStatus","待评价");
                }else if(status==3){
                    orderMap.put("orderStatus","已完成");
                }else if(status==4){
                    orderMap.put("orderStatus","交易取消");
                }
            }
        }
        gridMap.put("Rows",list);
        gridMap.put("Total",orderDao.getRows(map));
        return gridMap;
    }

    public int delete(long id) {
        return orderDao.delete(id);
    }

    public int update(Order order) {
        return orderDao.update(order);
    }
}

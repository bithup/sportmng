package com.xgh.mng.sercices;

import com.xgh.mng.basic.BaseService;
import com.xgh.mng.dao.IRefundDao;
import com.xgh.mng.entity.Refund;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/8.
 */
@Service("refundService")
public class RefundServiceImpl extends BaseService implements IRefundService {

    @Autowired
    protected IRefundDao refundDao;

    public int update(Refund refund) {
        return refundDao.update(refund);
    }

    public Refund get(long id) {
        return refundDao.get(id);
    }

    public Map<String, Object> getGridList(HttpServletRequest request) {
        Map<String,Object> gridMap = new HashMap<String, Object>();
        Map<String,Object> map = new HashMap<String, Object>();
        String page = request.getParameter("page");
        String pagesize = request.getParameter("pagesize");
        String orderNo = request.getParameter("orderNo");
        String account = request.getParameter("account");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String type = request.getParameter("type");
        String status = request.getParameter("status");
        long unitId = getCurrentUnitId(request);
        map.put("page",Integer.parseInt(page));
        map.put("pagesize",Integer.parseInt(pagesize));
        map.put("orderNo",orderNo);
        map.put("account",account);
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        map.put("unitId",unitId);
        if(Integer.parseInt(type)==1){
            map.put("status","0");
        }else if(Integer.parseInt(type)==2){
            map.put("status","");
        }
        if(null!=status&&!"".equals(status)){
            map.put("status",status);
        }
        List<Map<String,Object>> list = refundDao.getListPage(map);
        for(Map<String,Object> refundMap:list){
            if(Integer.parseInt(String.valueOf(refundMap.get("status")))==0){
                refundMap.put("status","待处理");
            }else if(Integer.parseInt(String.valueOf(refundMap.get("status")))==1){
                refundMap.put("status","退款成功");
            }else if(Integer.parseInt(String.valueOf(refundMap.get("status")))==2){
                refundMap.put("status","退款失败");
            }
        }
        gridMap.put("Rows",list);
        gridMap.put("Total",refundDao.getRows(map));
        return gridMap;
    }
}

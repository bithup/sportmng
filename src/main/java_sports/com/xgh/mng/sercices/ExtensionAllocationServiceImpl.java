package com.xgh.mng.sercices;

import com.xgh.mng.basic.BaseService;
import com.xgh.mng.dao.IExtensionAllocationDao;
import com.xgh.mng.entity.ExtensionAllocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @Author: cbj
 * @Description:
 * @Date: 2017/7/4
 */
@Service("extensionAllocationService")
public class ExtensionAllocationServiceImpl extends BaseService implements IExtensionAllocationService {

    @Autowired
    protected IExtensionAllocationDao extensionAllocationDao;

    public int update(ExtensionAllocation extensionAllocation) {
        return extensionAllocationDao.update(extensionAllocation);
    }


    public ExtensionAllocation get(long id) {
        ExtensionAllocation extensionAllocation = extensionAllocationDao.get(id);
        return extensionAllocation;
    }


    public List<Map<String, Object>> getList(Map<String, Object> map) {
        return extensionAllocationDao.getList(map);
    }

    public Map<String, Object> getGridList(HttpServletRequest request) {
        Map<String, Object> gridMap = new HashMap<String, Object>();
        Map<String, Object> map = new HashMap<String, Object>();
        String page = request.getParameter("page");
        String pagesize = request.getParameter("pagesize");
        map.put("page", Integer.parseInt(page));
        map.put("pagesize", Integer.parseInt(pagesize));
        List<Map<String,Object>>  extensionAllocationDaoList= extensionAllocationDao.getListMapPage(map);
        if (extensionAllocationDaoList == null) {
            extensionAllocationDaoList = new ArrayList<Map<String, Object>>();
        }
        for (Map<String,Object> extensionAllocationList_:extensionAllocationDaoList){
            String level1Allocation = "";
            String level2Allocation = "";
            String platformAllocation = "";
            String proporLevelOne = String.valueOf(extensionAllocationList_.get("proporLevelOne"));
            level1Allocation = "一级推广占比例"+proporLevelOne+"%";
            String orderLevelTwo = String.valueOf(extensionAllocationList_.get("orderLevelTwo"));
            level2Allocation = "二级推广每单得"+orderLevelTwo+"元";
            String orderPlatform = String.valueOf(extensionAllocationList_.get("orderPlatform"));
            platformAllocation = "平台每单得"+orderPlatform+"元";
            extensionAllocationList_.put("level1Allocation",level1Allocation);
            extensionAllocationList_.put("level2Allocation",level2Allocation);
            extensionAllocationList_.put("platformAllocation",platformAllocation);
        }
        gridMap.put("Rows", extensionAllocationDaoList);
        gridMap.put("Total", extensionAllocationDao.getRows(map));
        return gridMap;
    }

    public int save(HttpServletRequest request,ExtensionAllocation extensionAllocation){
        int flag = 0;
        if (extensionAllocation!=null&&extensionAllocation.getId()>0){
            ExtensionAllocation extensionAllocation1 = extensionAllocationDao.get(extensionAllocation.getId());
            extensionAllocation1.setProporLevelOne(extensionAllocation.getProporLevelOne());
            extensionAllocation1.setOrderLevelTwo(extensionAllocation.getOrderLevelTwo());
            extensionAllocation1.setOrderPlatform(extensionAllocation.getOrderPlatform());
            extensionAllocation1.setEnableStatus(extensionAllocation.getEnableStatus());
            extensionAllocation1.setUpdateDate(new Date());
            flag = extensionAllocationDao.update(extensionAllocation1);
        }else {
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("extensionType",extensionAllocation.getExtensionType());
            List<Map<String,Object>> extensionAllocationList = extensionAllocationDao.getList(map);
            if (extensionAllocationList!=null&&extensionAllocationList.size()>0){
                flag = -1;
            }else {
                extensionAllocation.setCreateDate(new Date());
                extensionAllocation.setStatus(1);
                flag = extensionAllocationDao.add(extensionAllocation);
            }
        }
        return flag;
    }

}
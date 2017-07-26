package com.xgh.mng.sercices;

import com.xgh.mng.basic.BaseService;
import com.xgh.mng.dao.IAosSubjectDao;
import com.xgh.mng.entity.*;
import com.xgh.util.ConstantUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by Administrator on 2016/11/10 0008.
 */
@Service("aosSubjectService")
public class AosSubjectServiceImpl extends BaseService implements IAosSubjectService {

    @Autowired
    protected IAosSubjectDao aosSubjectDao;

    @Autowired
    protected IFileDataServiceNew fileDataService;

    public int add(AosSubject aosSubject) {
        // TODO Auto-generated method stub
        return aosSubjectDao.add(aosSubject);
    }

    public int update(AosSubject aosSubject) {
        // TODO Auto-generated method stub
        return aosSubjectDao.update(aosSubject);
    }

    public int delete(long id) {
        return aosSubjectDao.deleteById(id);
    }


    public AosSubject get(long id) {
        AosSubject aosSubject= aosSubjectDao.get(id);
        return aosSubject;
    }

    public List<AosSubject> getList(Map<String,Object> map){
        return aosSubjectDao.getList(map);
    }

    public Map<String,Object> getGridList(HttpServletRequest request){
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> gridMap = new HashMap<String, Object>();
        String page = request.getParameter("page");
        String pagesize = request.getParameter("pagesize");
        String dataId = request.getParameter("dataId");
        map.put("page", Integer.parseInt(page));
        map.put("pagesize", Integer.parseInt(pagesize));
        map.put("unitId", getCurrentUnitId(request));
        map.put("dataId",dataId);
        List<Map<String,Object>> dataList = aosSubjectDao.getListPage(map);
        if (dataList == null) {
            dataList = new ArrayList<Map<String, Object>>();
        }
        for(Map<String,Object> dataList2:dataList){
            if (String.valueOf(dataList2.get("dataType"))!=null) {
                if (Integer.parseInt(String.valueOf(dataList2.get("dataType"))) == 1) {
                    dataList2.put("dataType", "场馆");
                } else if (Integer.parseInt(String.valueOf(dataList2.get("dataType"))) == 2) {
                    dataList2.put("dataType", "教练");
                } else if (Integer.parseInt(String.valueOf(dataList2.get("dataType"))) == 3) {
                    dataList2.put("dataType", "活动");
                }
            }
        }
        gridMap.put("Rows", dataList);
        gridMap.put("Total", aosSubjectDao.getRows(map));
        return gridMap;
    }

    public int save(HttpServletRequest request,AosSubject aosSubject) {
        SysUnits sysUnits = getCurrentUnits(request);
        SysIndustry sysIndustry = getCurrentIndustry(request);
        if (aosSubject != null && aosSubject.getId() < 1) {
            aosSubject.setUnitId(sysUnits.getId());
            aosSubject.setUnitCode(sysUnits.getUnitCode());
            aosSubject.setInstId(sysIndustry.getId());
            aosSubject.setInstCode(sysIndustry.getCode());
            aosSubject.setUserId(getCurrentUserId(request));
            Date date = new Date();
            aosSubject.setCreateDate(date);
            aosSubject.setUpdateDate(date);
            aosSubject.setStatus(1);
            return aosSubjectDao.add(aosSubject);
        } else {
            AosSubject subject1 = aosSubjectDao.get(aosSubject.getId());
            aosSubject.setInstId(getCurrentInstId(request));
            aosSubject.setInstCode(sysIndustry.getCode());
            aosSubject.setUnitId(getCurrentUnitId(request));
            aosSubject.setUnitCode(sysUnits.getUnitCode());
            aosSubject.setUserId(getCurrentUserId(request));
            aosSubject.setLogoPath(aosSubject.getLogoPath());
            aosSubject.setLogoRealPath(aosSubject.getLogoRealPath());
            aosSubject.setOrd(aosSubject.getOrd());
            aosSubject.setCreateDate(subject1.getCreateDate());
            aosSubject.setUpdateDate(new Date());
            aosSubject.setStatus(1);
            return aosSubjectDao.update(aosSubject);
        }
    }

}

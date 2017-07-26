package com.xgh.mng.sercices;

import com.xgh.mng.basic.BaseService;
import com.xgh.mng.dao.ICoachCourseDao;
import com.xgh.mng.entity.Coach;
import com.xgh.mng.entity.CoachCourse;
import com.xgh.mng.entity.SysUnits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by CQ on 2016/11/3.
 */
@Service("coachCourseService")
public class CoachCourseServiceImpl extends BaseService implements ICoachCourseService {

    @Autowired
    protected ICoachCourseDao coachCourseDao;


    public CoachCourse get(long id) {
        return coachCourseDao.get(id);
    }

    public List<CoachCourse> getList(CoachCourse coachCourse) {
        return coachCourseDao.getList(coachCourse);
    }

    public List<Map<String, Object>> getListPage(Map<String, Object> map) {
        return coachCourseDao.getListPage(map);
    }

    public long getRows(Map<String, Object> map) {
        return coachCourseDao.getRows(map);
    }

    public int add(CoachCourse coachCourse) {
        return coachCourseDao.add(coachCourse);
    }

    public int update(CoachCourse coachCourse) {
        return coachCourseDao.update(coachCourse);
    }

    public int deleteById(long id) {
        return coachCourseDao.deleteById(id);
    }

    public int deleteByNid(long nid) {
        return 0;
    }

    public int save(HttpServletRequest request, CoachCourse coachCourse, String op) {
        if ("modify".equals(op)) {
            CoachCourse coachCourse1 = coachCourseDao.get(coachCourse.getId());
            Date date = new Date();
            coachCourse.setCreateDate(coachCourse1.getCreateDate());
            coachCourse.setUpdateDate(date);
            coachCourse.setStatus(1);
            return coachCourseDao.update(coachCourse);
        } else {
            Date date = new Date();
            coachCourse.setCreateDate(date);
            coachCourse.setUpdateDate(date);
            coachCourse.setStatus(1);
            return coachCourseDao.add(coachCourse);
        }

    }

    public Map<String, Object> getGridList(HttpServletRequest request) {
        String page = request.getParameter("page");
        String pagesize = request.getParameter("pagesize");
        String coachId = request.getParameter("coachId");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("unitId", getCurrentUnitId(request));
        map.put("instId", getCurrentInstId(request));
        map.put("page", Integer.parseInt(page));
        map.put("pagesize", Integer.parseInt(pagesize));
        map.put("coachId", coachId);
        System.out.println("教练课程..........");
        Map<String, Object> gridMap = new HashMap<String, Object>();
        List<Map<String, Object>> dataList = coachCourseDao.getListPage(map);
        if (dataList != null && dataList.size() > 0) {
            for (Map<String, Object> map_ : dataList) {
                String courseType = map_.get("courseType") + "";
                if ("1".equals(courseType)) {
                    map_.put("courseType", "月");
                }
                if ("2".equals(courseType)) {
                    map_.put("courseType", "季");
                }
                if ("3".equals(courseType)) {
                    map_.put("courseType", "年");
                }
            }
        }
        if (dataList == null) {
            dataList = new ArrayList<Map<String, Object>>();
        }
        gridMap.put("Rows", dataList);
        gridMap.put("Total", coachCourseDao.getRows(map));
        return gridMap;
    }
}

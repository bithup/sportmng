package com.xgh.mng.dao;

import com.xgh.mng.dao.read.ICoachCourseDaoR;
import com.xgh.mng.dao.write.ICoachCourseDaoW;
import com.xgh.mng.entity.CoachCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by CQ on 2016/11/3.
 */
@Service("coachCourseDao")
public class CoachCourseDaoImpl implements ICoachCourseDao {

    @Autowired
    protected ICoachCourseDaoR coachCourseDaoR;
    @Autowired
    protected ICoachCourseDaoW coachCourseDaoW;

    public CoachCourse get(long id) {
        return coachCourseDaoR.get(id);
    }

    public List<CoachCourse> getList(CoachCourse coachCourse) {
        return coachCourseDaoR.getList(coachCourse);
    }

    public List<Map<String, Object>> getListPage(Map<String, Object> map) {
        return coachCourseDaoR.getListPage(map);
    }

    public long getRows(Map<String, Object> map) {
        return coachCourseDaoR.getRows(map);
    }

    public int add(CoachCourse coachCourse) {
        return coachCourseDaoW.add(coachCourse);
    }

    public int update(CoachCourse coachCourse) {
        return coachCourseDaoW.update(coachCourse);
    }

    public int deleteById(long id) {
        return coachCourseDaoW.deleteById(id);
    }

    public int deleteByNid(long nid) {
        return coachCourseDaoW.deleteByNid(nid);
    }

    public int save(HttpServletRequest request, CoachCourse coachCourse, String op) {
        return coachCourseDaoW.add(coachCourse);
    }
}

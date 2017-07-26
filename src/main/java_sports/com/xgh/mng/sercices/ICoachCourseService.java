package com.xgh.mng.sercices;

import com.xgh.mng.entity.CoachCourse;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by CQ on 2016/11/3.
 */
public interface ICoachCourseService {

    /**
     * get
     *
     * @return
     */
    public CoachCourse get(long id);

    /**
     * getList
     *
     * @return
     */
    public List<CoachCourse> getList(CoachCourse coachCourse);

    public Map<String, Object> getGridList(HttpServletRequest request);

    /**
     * getListPage
     * <p/>
     * page,pagesize,key
     *
     * @return
     */
    public List<Map<String, Object>> getListPage(Map<String, Object> map);

    /**
     * getRows
     *
     * @param map
     * @return id desc,name ,date asc
     */
    public long getRows(Map<String, Object> map);


    /**
     * add
     */
    public int add(CoachCourse coachCourse);

    /**
     * update
     */
    public int update(CoachCourse coachCourse);

    /**
     * delete
     */
    public int deleteById(long id);

    /**
     * deleteByNid
     */
    public int deleteByNid(long nid);

    public int save(HttpServletRequest request, CoachCourse coachCourse, String op);
}

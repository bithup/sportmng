package com.xgh.mng.dao.write;

import com.xgh.mng.entity.Coach;
import com.xgh.mng.entity.CoachCourse;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by CQ on 2016/11/3.
 */
public interface ICoachCourseDaoW {


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

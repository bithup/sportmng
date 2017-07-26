package com.xgh.mng.dao.read;


import com.xgh.mng.entity.CoachCourse;

import java.util.List;
import java.util.Map;

/**
 * Created by CQ on 2016/11/3.
 */
public interface ICoachCourseDaoR {


    /**
     * get
     * @return
     */
    public CoachCourse get(long id);

    /**
     * getList
     * @return
     */
    public List<CoachCourse> getList(CoachCourse coachCourse);


    /**
     * getListPage
     *
     * page,pagesize,key
     * @return
     */
    public List<Map<String,Object>> getListPage(Map<String, Object> map);

    /**
     * getRows
     * @param map
     * @return  id desc,name ,date asc
     */
    public long getRows(Map<String, Object> map);

}

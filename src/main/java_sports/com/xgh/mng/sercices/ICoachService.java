package com.xgh.mng.sercices;

import com.xgh.mng.entity.Coach;
import com.xgh.mng.entity.Kinds;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by CQ on 2016/11/2.
 */
public interface ICoachService {

    /**
     * get
     *
     * @return
     */
    public Coach get(long id);

    /**
     * getList
     *
     * @return
     */
    public List<Coach> getList(Coach coach);

    /**
     * @param request
     * @return
     */
    public Map<String, Object> getGridList(HttpServletRequest request);

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
    public int add(Coach coach);

    /**
     * update
     */
    public int update(Coach coach);

    /**
     * delete
     */
    public int deleteById(long id);

    /**
     * deleteByNid
     */
    public int deleteByNid(long nid);


    public int save(HttpServletRequest request, Coach coach, String op);
    
    public int checkCoach(Coach coach);

}

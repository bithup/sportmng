package com.xgh.mng.dao.write;

import com.xgh.mng.entity.Coach;
import com.xgh.mng.entity.Kinds;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by CQ on 2016/11/2.
 */
public interface ICoachDaoW {

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

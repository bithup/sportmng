package com.xgh.mng.dao.read;

import com.xgh.mng.entity.Coach;
import com.xgh.mng.entity.Kinds;

import java.util.List;
import java.util.Map;

/**
 * Created by CQ on 2016/11/2.
 */
public interface ICoachDaoR {

    /**
     * get
     * @return
     */
    public Coach get(long id);

    /**
     * getList
     * @return
     */
    public List<Coach> getList(Coach coach);


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

package com.xgh.mng.dao;

import com.xgh.mng.entity.Subject;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/8 0008.
 */
public interface ISubjectDao {

    /**
     * add
     */
    public int add(Subject subject);

    /**
     * update
     */
    public int update(Subject subject);

    /**
     * get
     * @return
     */
    public Subject get(long id);


    /**
     * getList
     * @return
     */
    public List<Subject> getList(Map<String, Object> map);


    /**
     * getListPage
     *
     * page,pagesize,key
     * @return
     */
    public List<Map<String,Object>> getListPage(Map<String,Object> map);

    /**
     * getRows
     * @param map
     * @return  id desc,name ,date asc
     */
    public long getRows(Map<String, Object> map);

    /**
     * 通过unitId获取subject Tree
     * @param map
     * @return
     */
    public List<Map<String,Object>> getSubjectTree(Map<String,Object> map);
}

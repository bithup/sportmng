package com.xgh.mng.dao;

import com.xgh.mng.entity.AosSubject;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/10 0008.
 */
public interface IAosSubjectDao {

    /**
     * add
     */
    public int add(AosSubject aosSubject);

    /**
     * update
     */
    public int update(AosSubject aosSubject);

    /**
     * delete
     */
    public int deleteById(long id);

    /**
     * get
     * @return
     */
    public AosSubject get(long id);


    /**
     * getList
     * @return
     */
    public List<AosSubject> getList(Map<String, Object> map);


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

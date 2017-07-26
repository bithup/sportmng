package com.xgh.mng.dao.read;

import com.xgh.mng.entity.AosSubject;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/10 0008.
 */
public interface IAosSubjectDaoR {

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

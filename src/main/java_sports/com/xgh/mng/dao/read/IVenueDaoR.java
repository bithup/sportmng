package com.xgh.mng.dao.read;

import com.xgh.mng.entity.Venue;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/19.
 */
public interface IVenueDaoR {

    public Venue get(long id);

    public List<Map<String,Object>> getListPage(Map<String,Object> map);

    public int getRows(Map<String,Object> map);

    List<Map<String, Object>> getVenue(Map<String, Object> var1);
}

package com.xgh.mng.dao.read;

import com.xgh.mng.entity.ChildVenue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/27.
 */
public interface IChildVenueDaoR {

    public ChildVenue get(long id);

    public List<Map<String,Object>> getList(HashMap<String,Object> map);

    public  List<Map<String,Object>> getListPage(Map<String,Object> map);

    public int getRows(Map<String,Object> map);
}

package com.xgh.mng.sercices;

import com.xgh.mng.entity.ChildVenue;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/27.
 */
public interface IChildVenueService {

    public ChildVenue get(long id);

    public List<Map<String,Object>> getList(HashMap<String,Object> map);

    public int insert(ChildVenue childVenue);

    public int update(ChildVenue childVenue);

    public int delete(long id);

    public int save(HttpServletRequest request,ChildVenue childVenue);

    public  Map<String,Object> getGridList(HttpServletRequest request);

}

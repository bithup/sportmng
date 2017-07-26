package com.xgh.mng.sercices;

import com.xgh.mng.entity.Venue;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/19.
 */
public interface IVenueService {

    public Venue get(long id);

    public Map<String,Object> getListPage(Map<String,Object> map);

    public int add(Venue venue);

    public int update(Venue venue);

    public int delete(long id);

    public int save(HttpServletRequest request,Venue venue);

    List<Map<String, Object>> getVenue(long var1);

    /**
     * 场馆审核
     * @param venue
     * @return
     */
    public int checkVenue(Venue venue);

}

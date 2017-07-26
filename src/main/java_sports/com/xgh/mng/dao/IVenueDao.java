package com.xgh.mng.dao;

import com.xgh.mng.entity.Venue;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/19.
 */
public interface IVenueDao {

    public Venue get(long id);

    public List<Map<String,Object>> getListPage(Map<String,Object> map);

    public int getRows(Map<String,Object> map);

    public int add(Venue venue);

    public int update(Venue venue);

    public int delete(long id);

    List<Map<String, Object>> getVenue(Map<String, Object> var1);

    public int checkVenue(Venue venue);

}

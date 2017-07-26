package com.xgh.mng.dao;

import com.xgh.mng.dao.read.IKindsDaoR;
import com.xgh.mng.dao.read.IVenueDaoR;
import com.xgh.mng.dao.write.IKindsDaoW;
import com.xgh.mng.dao.write.IVenueDaoW;
import com.xgh.mng.entity.Venue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/19.
 */
@Service("venueDao")
public class VenueDaoImpl implements  IVenueDao{

    @Autowired
    protected IVenueDaoR venueDaoR;
    @Autowired
    protected IVenueDaoW venueDaoW;


    public Venue get(long id) {
        return venueDaoR.get(id);
    }

    public List<Map<String, Object>> getListPage(Map<String, Object> map) {
        return venueDaoR.getListPage(map);
    }

    public int getRows(Map<String, Object> map) {
        return venueDaoR.getRows(map);
    }

    public int add(Venue venue) {
        return venueDaoW.add(venue);
    }

    public int update(Venue venue) {
        return venueDaoW.update(venue);
    }

    public int delete(long id) {
        return venueDaoW.delete(id);
    }

    public List<Map<String, Object>> getVenue(Map<String, Object> map) {
        return this.venueDaoR.getVenue(map);
    }

    public int checkVenue(Venue venue){
        return venueDaoW.checkVenue(venue);
    }
}

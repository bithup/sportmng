package com.xgh.mng.dao;

import com.xgh.mng.dao.read.IChildVenueDaoR;
import com.xgh.mng.dao.write.IChildVenueDaoW;
import com.xgh.mng.entity.ChildVenue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/27.
 */
@Service("childVenueDao")
public class ChildVenueDaoImpl implements IChildVenueDao {

    @Autowired
    protected IChildVenueDaoR childVenueDaoR;

    @Autowired
    protected IChildVenueDaoW childVenueDaoW;

    public ChildVenue get(long id) {
        return childVenueDaoR.get(id);
    }

    public List<Map<String, Object>> getList(HashMap<String,Object> map) {
        return childVenueDaoR.getList(map);
    }

    public int insert(ChildVenue childVenue) {
        return childVenueDaoW.insert(childVenue);
    }

    public int update(ChildVenue childVenue) {
        return childVenueDaoW.update(childVenue);
    }

    public int delete(long id) {
        return childVenueDaoW.delete(id);
    }

    public List<Map<String, Object>> getListPage(Map<String, Object> map) {
        return childVenueDaoR.getListPage(map);
    }

    public int getRows(Map<String, Object> map) {
        return childVenueDaoR.getRows(map);
    }
}

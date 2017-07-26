package com.xgh.mng.dao.write;

import com.xgh.mng.entity.ChildVenue;

/**
 * Created by Administrator on 2016/10/27.
 */
public interface IChildVenueDaoW {

    public int insert(ChildVenue childVenue);

    public int update(ChildVenue childVenue);

    public int delete(long id);

}

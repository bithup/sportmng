package com.xgh.mng.dao.write;

import com.xgh.mng.entity.Venue;

/**
 * Created by Administrator on 2016/10/19.
 */
public interface IVenueDaoW {

    public int add(Venue venue);

    public int update(Venue venue);

    public int delete(long id);

    public int checkVenue(Venue venue);

}

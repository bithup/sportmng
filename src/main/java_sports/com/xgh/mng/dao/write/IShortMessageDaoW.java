package com.xgh.mng.dao.write;

import com.xgh.mng.entity.ShortMessage;

/**
 * Created by Administrator on 2016/11/23.
 */
public interface IShortMessageDaoW {

    public int update(ShortMessage shortMessage);

    public int insert(ShortMessage shortMessage);
}

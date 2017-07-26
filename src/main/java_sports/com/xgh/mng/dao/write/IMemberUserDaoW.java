package com.xgh.mng.dao.write;

import com.xgh.mng.entity.MemberUser;

/**
 * Created by Administrator on 2016/11/7.
 */
public interface IMemberUserDaoW {

    public int insert(MemberUser memberUser);

    public int update(MemberUser memberUser);

    public int delete(long id);

}

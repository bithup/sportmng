package com.xgh.mng.dao.write;

import com.xgh.mng.entity.Refund;

import java.sql.Ref;

/**
 * Created by Administrator on 2016/11/8.
 */
public interface IRefundDaoW {

    public int insert(Refund refund);

    public int delete(long id);

    public int update(Refund refund);
}

package com.xgh.mng.dao.write;

import com.xgh.mng.entity.Withdrawals;

/**
 * Created by Administrator on 2016/12/23.
 */
public interface IWithdrawalsDaoW {

    public int delete(long id);

    public int insert(Withdrawals withdrawals);

    public int update(Withdrawals withdrawals);
}

package com.xgh.mng.dao.write;


import com.xgh.mng.entity.TransactionRecord;

/**
 * Created by Administrator on 2016/10/22.
 */
public interface ITransactionRecordDaoW {

    public int insert(TransactionRecord transactionRecord);

    public int delete(long id);

    public int update(TransactionRecord transactionRecord);
}

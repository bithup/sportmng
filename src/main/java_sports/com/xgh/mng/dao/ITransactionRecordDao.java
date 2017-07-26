package com.xgh.mng.dao;


import com.xgh.mng.entity.TransactionRecord;

/**
 * Created by Administrator on 2016/10/22.
 */
public interface ITransactionRecordDao {

    public TransactionRecord get(long id);

    public int insert(TransactionRecord transactionRecord);

    public int delete(long id);

    public int update(TransactionRecord transactionRecord);
}

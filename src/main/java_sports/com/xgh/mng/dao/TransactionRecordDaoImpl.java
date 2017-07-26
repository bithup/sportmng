package com.xgh.mng.dao;

import com.xgh.mng.dao.read.ITransactionRecordDaoR;
import com.xgh.mng.dao.write.ITransactionRecordDaoW;
import com.xgh.mng.entity.TransactionRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/10/22.
 */
@Service("transactionRecordDao")
public class TransactionRecordDaoImpl implements ITransactionRecordDao {

    @Autowired
    protected ITransactionRecordDaoR transactionRecordDaoR;
    @Autowired
    protected ITransactionRecordDaoW transactionRecordDaoW;

    public TransactionRecord get(long id) {
        return transactionRecordDaoR.get(id);
    }

    public int insert(TransactionRecord transactionRecord) {

        return transactionRecordDaoW.insert(transactionRecord);
    }

    public int delete(long id) {

        return transactionRecordDaoW.delete(id);
    }

    public int update(TransactionRecord transactionRecord) {

        return transactionRecordDaoW.update(transactionRecord);
    }
}

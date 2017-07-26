package com.xgh.mng.dao;

import com.xgh.mng.dao.read.IWithdrawalsDaoR;
import com.xgh.mng.dao.write.IWithdrawalsDaoW;
import com.xgh.mng.entity.Withdrawals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/23.
 */
@Service("withdrawalsDao")
public class WithdrawalsDaoImpl implements IWithdrawalsDao {

    @Autowired
    protected IWithdrawalsDaoR withdrawalsDaoR;

    @Autowired
    protected IWithdrawalsDaoW withdrawalsDaoW;


    public Withdrawals get(long id) {
        return withdrawalsDaoR.get(id);
    }

    public List<Map<String, Object>> getListPage(Map<String,Object> map) {
        return withdrawalsDaoR.getListPage(map);
    }

    public int getRows(Map<String, Object> map) {
        return withdrawalsDaoR.getRows(map);
    }

    public int delete(long id) {
        return withdrawalsDaoW.delete(id);
    }

    public int insert(Withdrawals withdrawals) {
        return withdrawalsDaoW.insert(withdrawals);
    }

    public int update(Withdrawals withdrawals) {
        return withdrawalsDaoW.update(withdrawals);
    }
}

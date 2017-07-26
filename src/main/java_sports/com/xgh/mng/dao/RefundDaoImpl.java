package com.xgh.mng.dao;

import com.xgh.mng.dao.read.IRefundDaoR;
import com.xgh.mng.dao.write.IRefundDaoW;
import com.xgh.mng.entity.Refund;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/8.
 */
@Service("refundDao")
public class RefundDaoImpl implements IRefundDao {

    @Autowired
    protected IRefundDaoR refundDaoR;

    @Autowired
    protected IRefundDaoW refundDaoW;

    public int insert(Refund refund) {
        return refundDaoW.insert(refund);
    }

    public int delete(long id) {
        return refundDaoW.delete(id);
    }

    public int update(Refund refund) {
        return refundDaoW.update(refund);
    }

    public Refund get(long id) {
        return refundDaoR.get(id);
    }

    public List<Map<String, Object>> getListPage(Map<String, Object> map) {
        return refundDaoR.getListPage(map);
    }

    public int getRows(Map<String, Object> map) {
        return refundDaoR.getRows(map);
    }
}

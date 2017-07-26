package com.xgh.mng.dao.read;

import com.xgh.mng.entity.Refund;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/8.
 */
public interface IRefundDaoR {

    public Refund get(long id);

    public List<Map<String,Object>> getListPage(Map<String,Object> map);

    public int getRows(Map<String,Object> map);
}

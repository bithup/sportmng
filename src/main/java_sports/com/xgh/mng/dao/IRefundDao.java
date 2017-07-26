package com.xgh.mng.dao;

import com.xgh.mng.entity.Refund;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/8.
 */
public interface IRefundDao {

    public int insert(Refund refund);

    public int delete(long id);

    public int update(Refund refund);

    public Refund get(long id);

    public List<Map<String,Object>> getListPage(Map<String,Object> map);

    public int getRows(Map<String,Object> map);
}

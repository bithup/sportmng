package com.xgh.mng.dao;

import com.xgh.mng.entity.Withdrawals;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/23.
 */
public interface IWithdrawalsDao {


    public Withdrawals get(long id);


    public List<Map<String,Object>> getListPage(Map<String, Object> map);


    public int getRows(Map<String, Object> map);


    public int delete(long id);


    public int insert(Withdrawals withdrawals);


    public int update(Withdrawals withdrawals);
}

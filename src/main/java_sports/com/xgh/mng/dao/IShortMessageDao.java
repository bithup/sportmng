package com.xgh.mng.dao;

import com.xgh.mng.entity.ShortMessage;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/23.
 */
public interface IShortMessageDao {

    public ShortMessage get(long id);

    public List<ShortMessage> getListPage(Map<String,Object> map);

    public int getRows(Map<String,Object> map);

    public int update(ShortMessage shortMessage);

    public int insert(ShortMessage shortMessage);
}

package com.xgh.mng.dao.read;

import com.sun.javafx.collections.MappingChange;
import com.xgh.mng.entity.ShortMessage;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/23.
 */
public interface IShortMessageDaoR {

    public ShortMessage get(long id);

    public List<ShortMessage> getListPage(Map<String,Object> map);

    public int getRows(Map<String,Object> map);
}

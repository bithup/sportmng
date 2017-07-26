package com.xgh.mng.dao;

import com.xgh.mng.dao.read.IShortMessageDaoR;
import com.xgh.mng.dao.write.IShortMessageDaoW;
import com.xgh.mng.entity.ShortMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/23.
 */
@Service("shortMessageDao")
public class ShortMessageDaoImpl implements IShortMessageDao {

    @Autowired
    protected IShortMessageDaoR shortMessageDaoR;

    @Autowired
    protected IShortMessageDaoW shortMessageDaoW;

    public ShortMessage get(long id) {
        return shortMessageDaoR.get(id);
    }

    public List<ShortMessage> getListPage(Map<String, Object> map) {
        return shortMessageDaoR.getListPage(map);
    }

    public int getRows(Map<String, Object> map) {
        return shortMessageDaoR.getRows(map);
    }

    public int update(ShortMessage shortMessage) {
        return shortMessageDaoW.update(shortMessage);
    }

    public int insert(ShortMessage shortMessage) {
        return shortMessageDaoW.insert(shortMessage);
    }
}

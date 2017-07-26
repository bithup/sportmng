package com.xgh.mng.dao;

import com.xgh.mng.dao.read.IKindsDaoR;
import com.xgh.mng.dao.write.IKindsDaoW;
import com.xgh.mng.entity.Kinds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * KindsDao Impl
 *
 * @author h2y
 *         <p/>
 *         time:2015-12-28 10:58:10
 *         <p/>
 *         Gmail:
 */
@Service("kindsDao")
public class KindsDaoImpl implements IKindsDao {


    @Autowired
    protected IKindsDaoR kindsDaoR;

    @Autowired
    protected IKindsDaoW kindsDaoW;

    /**
     * add
     */
    public int add(Kinds kinds) {
        return kindsDaoW.add(kinds);
    }

    /**
     * update
     */
    public int update(Kinds kinds) {
        return kindsDaoW.update(kinds);
    }

    /**
     * delete
     */
    public int deleteById(long id) {
        return kindsDaoW.deleteById(id);
    }

    /**
     * get
     *
     * @return
     */
    public Kinds get(long id) {
        return kindsDaoR.get(id);
    }


    /**
     * getList
     *
     * @return
     */
    public List<Kinds> getList(Kinds kinds) {
        return kindsDaoR.getList(kinds);
    }


    /**
     * getListPage
     * page,pagesize,key
     *
     * @return
     */
    public List<Kinds> getListPage(Map<String, Object> map) {
        return kindsDaoR.getListPage(map);
    }

    /**
     * getRows
     *
     * @param map
     * @return id desc,name ,date asc
     */
    public long getRows(Map<String, Object> map) {
        return kindsDaoR.getRows(map);
    }


    /**
     * 通过 parenId 获取子级的分类
     * @param map
     * @return
     */
    public List<Map<String, Object>> getChildTreeData(Map<String, Object> map) {
        return kindsDaoR.getChildTreeData(map);
    }

    /**
     * 判断是否有子级
     * @param parentId
     * @return
     */
    public long isHasChild(long parentId) {
        return kindsDaoR.isHasChild(parentId);
    }


    public List<Kinds> getChildData(Map<String, Object> map) {
        return kindsDaoR.getChildData(map);
    }
}
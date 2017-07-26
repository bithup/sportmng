package com.xgh.mng.dao;

import com.xgh.mng.dao.read.ISubjectDaoR;
import com.xgh.mng.dao.write.ISubjectDaoW;
import com.xgh.mng.entity.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/8 0008.
 */
@Service("subjectDao")
public class SubjectDaoImpl implements ISubjectDao {

    @Autowired
    protected ISubjectDaoR subjectDaoR;

    @Autowired
    protected ISubjectDaoW subjectDaoW;

    /**
     * add
     */
    public int add(Subject subject){
        return subjectDaoW.add(subject);
    }

    /**
     * update
     */
    public int update(Subject subject){
        return subjectDaoW.update(subject);
    }

    /**
     * get
     * @return
     */
    public Subject get(long id){
        return subjectDaoR.get(id);
    }


    /**
     * getList
     * @return
     */
    public List<Subject> getList(Map<String,Object> map){
        return subjectDaoR.getList(map);
    }


    /**
     * getListPage
     *
     * page,pagesize,key
     * @return
     */
    public List<Map<String, Object>> getListPage(Map<String, Object> map) {
        return subjectDaoR.getListPage(map);
    }

    /**
     * getRows
     * @param map
     * @return  id desc,name ,date asc
     */
    public long getRows(Map<String,Object> map){
        return subjectDaoR.getRows(map);
    }

    /**
     * 通过unitId获取subject Tree
     * @param map
     * @return
     */
    public List<Map<String,Object>> getSubjectTree(Map<String,Object> map){
        return subjectDaoR.getSubjectTree(map);
    }

}

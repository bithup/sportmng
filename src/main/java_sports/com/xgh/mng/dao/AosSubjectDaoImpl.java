package com.xgh.mng.dao;

import com.xgh.mng.dao.read.IAosSubjectDaoR;
import com.xgh.mng.dao.write.IAosSubjectDaoW;
import com.xgh.mng.entity.AosSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/10 0008.
 */
@Service("aosSubjectDao")
public class AosSubjectDaoImpl implements IAosSubjectDao{

    @Autowired
    protected IAosSubjectDaoR aosSubjectDaoR;

    @Autowired
    protected IAosSubjectDaoW aosSubjectDaoW;

    /**
     * add
     */
    public int add(AosSubject aosSubject){
        return aosSubjectDaoW.add(aosSubject);
    }

    /**
     * update
     */
    public int update(AosSubject aosSubject){
        return aosSubjectDaoW.update(aosSubject);
    }

    /**
     * delete
     */
    public int deleteById(long id){
        return aosSubjectDaoW.deleteById(id);
    }

    /**
     * get
     * @return
     */
    public AosSubject get(long id){
        return aosSubjectDaoR.get(id);
    }


    /**
     * getList
     * @return
     */
    public List<AosSubject> getList(Map<String,Object> map){
        return aosSubjectDaoR.getList(map);
    }


    /**
     * getListPage
     *
     * page,pagesize,key
     * @return
     */
    public List<Map<String, Object>> getListPage(Map<String, Object> map) {
        return aosSubjectDaoR.getListPage(map);
    }

    /**
     * getRows
     * @param map
     * @return  id desc,name ,date asc
     */
    public long getRows(Map<String,Object> map){
        return aosSubjectDaoR.getRows(map);
    }

}

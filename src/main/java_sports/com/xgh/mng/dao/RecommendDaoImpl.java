package com.xgh.mng.dao;


import com.xgh.mng.dao.read.IRecommendDaoR;
import com.xgh.mng.dao.write.IRecommendDaoW;
import com.xgh.mng.entity.Recommend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by scx on 2016/2/25.
 */
@Service("recommendDao")
public class RecommendDaoImpl implements IRecommendDao  {
    @Autowired
    protected IRecommendDaoR recommendDaoR;

    @Autowired
    protected IRecommendDaoW recommendDaoW;

    /**
     * add
     */
    public int add(Recommend recommend){
        return recommendDaoW.add(recommend);
    }



    /**
     * update
     */
    public int update(Recommend recommend){
        return recommendDaoW.update(recommend);
    }

    public int addBatch(List<Recommend> list) {
        return 0;
    }

    /**
     *   deleteById
     * @param id
     * @return
     */
    public int deleteById(long id) {
        return recommendDaoW.deleteById(id);
    }

    /**
     * get
     * @return
     */
    public Recommend get(long id){
        return recommendDaoR.get(id);
    }


    /**
     * getList
     * @return
     */
    public List<Recommend> getList(Map<String,Object> map){
        return recommendDaoR.getList(map);
    }

    /**
     * getListPage
     *
     * page,pagesize,key
     * @return
     */
    public List<Recommend> getListPage(Map<String,Object> map){
        return recommendDaoR.getListPage(map);
    }

    /**
     * getRows
     * @param map
     * @return  id desc,name ,date asc
     */
    public long getRows(Map<String,Object> map){
        return recommendDaoR.getRows(map);
    }

}

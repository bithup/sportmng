package com.xgh.mng.dao.write;

import com.xgh.mng.entity.Recommend;

import java.util.List;

/**
 * Created by xiaowenbo on 2016/3/28 0028.
 */
public interface IRecommendDaoW {
    /**
     * 添加反馈
     * @param recommend
     * @return
     */
     public Integer add(Recommend recommend);


    /**
     * update
     */
    public int update(Recommend recommend);

    /**
     *   删除反馈
     * @param id
     * @return
     */
    public int deleteById(long id);

    /**
     *    批量添加
     * @param list
     * @return
     */
    public int addBatch(List<Recommend> list);
}

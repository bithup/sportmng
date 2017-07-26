package com.xgh.mng.dao.write;

import com.xgh.mng.entity.AosSubject;

/**
 * Created by Administrator on 2016/11/10 0008.
 */
public interface IAosSubjectDaoW {

    /**
     * add
     */
    public int add(AosSubject aosSubject);

    /**
     * update
     */
    public int update(AosSubject aosSubject);

    /**
     * delete
     */
    public int deleteById(long id);

}

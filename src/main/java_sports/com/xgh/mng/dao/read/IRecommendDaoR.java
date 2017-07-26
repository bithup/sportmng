package com.xgh.mng.dao.read;


import com.xgh.mng.entity.Recommend;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by scx on 2016/2/25.
 */
@Component
public interface IRecommendDaoR {

        /**
         * get
         * @return
         */
        public Recommend get(long id);

        /**
         * getList
         * @return
         */
        public List<Recommend> getList(Map<String, Object> map);

        /**
         * getListPage
         * <p/>
         * page,pagesize,key
         *
         * @return
         */
        public List<Recommend> getListPage(Map<String, Object> map);

        /**
         * getRows
         *
         * @param map
         * @return id desc,name ,date asc
         */
        public long getRows(Map<String, Object> map);



}

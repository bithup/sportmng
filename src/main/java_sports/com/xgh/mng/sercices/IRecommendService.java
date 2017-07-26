package com.xgh.mng.sercices;

import com.xgh.mng.entity.Recommend;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by scx on 2016/2/25.
 */
public interface IRecommendService {

    public int add(Recommend recommend);
    public int update(Recommend recommend);
    public int save(HttpServletRequest request, Recommend recommend);
    public int delete(long id);
    public Recommend get(long id);
    public List<Recommend> getList(Map<String, Object> map);
    public Map<String,Object> getGridList(HttpServletRequest request);
}

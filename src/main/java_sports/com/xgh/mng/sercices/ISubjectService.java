package com.xgh.mng.sercices;

import com.xgh.mng.entity.Subject;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/8 0008.
 */
public interface ISubjectService {

    public Subject get(long id);

    public List<Subject> getList(Map<String, Object> map);

    public Map<String,Object> getGridList(HttpServletRequest request);

    public int save(HttpServletRequest request,Subject subject);
    /**
     * 获取活动树
     * @param map
     * @return
     */
    public List<Map<String,Object>> getSubjectTree(Map<String,Object> map);
}

package com.xgh.mng.sercices;

import com.xgh.mng.entity.Activity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * ActivityService
 *
 * @author h2y
 * @time:2016-01-23 11:48:18
 * @Email:
 */
public interface IActivityService {

    public int add(Activity activity);

    public int update(Activity activity);

    public int save(HttpServletRequest request, Activity activity);

    public int delete(long id);

    public Activity get(long id);

    public List<Activity> getList(Map<String, Object> map);

    public Map<String, Object> getGridList(HttpServletRequest request);

    /**
     * 活动审核
     * @param activity
     * @return
     */
    public int checkActivity(Activity activity);

    /**
     * 获取活动树
     * @param map
     * @return
     */
    public List<Map<String,Object>> getActivityTree(Map<String, Object> map);
}

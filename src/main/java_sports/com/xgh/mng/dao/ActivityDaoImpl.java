package com.xgh.mng.dao;

import com.xgh.mng.dao.read.IActivityDaoR;
import com.xgh.mng.dao.write.IActivityDaoW;
import com.xgh.mng.entity.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * ActivityDao Impl
 *
 * @author h2y
 *
 * @time:2016-11-01
 *
 * @Email:
 */
@Service("activityDao")
public class ActivityDaoImpl implements IActivityDao {
 
	@Autowired
	protected IActivityDaoR activityDaoR;

	@Autowired
	protected IActivityDaoW activityDaoW;
	
	/**
	 * add
	 */
	public int add(Activity activity){
		return activityDaoW.add(activity);
	}
	
	/**
	 * addBatch
	 */
	public int addBatch(List<Activity> list){
		return activityDaoW.addBatch(list);
	}
	
	/**
	 * update
	 */
	public int update(Activity activity){
		return activityDaoW.update(activity);
	}
	
	/**
	 * delete
	 */
	public int deleteById(long id){
		return activityDaoW.deleteById(id);
	}

	/**
	 * get
	 * @return
	 */
	public Activity get(long id){
		return activityDaoR.get(id);
	}
	
	
	/**
	 * getList
	 * @return
	 */
	public List<Activity> getList(Map<String,Object> map){
		return activityDaoR.getList(map);
	}
	

	/**
	 * getListPage
	 * 
	 * page,pagesize,key
	 * @return
	 */
	public List<Map<String, Object>> getListPage(Map<String, Object> map) {
		return activityDaoR.getListPage(map);
	}


	/**
	 * getRows
	 * @param map
	 * @return  id desc,name ,date asc
	 */  
	public long getRows(Map<String,Object> map){
		return activityDaoR.getRows(map);
	}

	/**
	 * 活动审核
	 * @param activity
	 * @return
	 */
	public int checkActivity(Activity activity) {
		return activityDaoW.checkActivity(activity);
	}

	/**
	 * 获取活动树
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getActivityTree(Map<String,Object> map){
		return activityDaoR.getActivityTree(map);
	}
}
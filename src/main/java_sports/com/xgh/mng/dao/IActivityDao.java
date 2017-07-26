package com.xgh.mng.dao;

import com.xgh.mng.entity.Activity;

import java.util.List;
import java.util.Map;

/**
 * ActivityDao read
 *
 * @author h2y
 *
 * @time:2016-11-01
 *
 * @Email:
 */
public interface IActivityDao {

	/**
	 * add
	 */
	public int add(Activity activity);
	
	/**
	 * addBatch
	 */
	public int addBatch(List<Activity> list);
	
	/**
	 * update
	 */
	public int update(Activity activity);
	
	/**
	 * delete
	 */
	public int deleteById(long id);

	/**
	 * get
	 * @return
	 */
	public Activity get(long id);
	
	
	/**
	 * getList
	 * @return
	 */
	public List<Activity> getList(Map<String, Object> map);
	

	/**
	 * getListPage
	 * 
	 * page,pagesize,key
	 * @return
	 */
	public List<Map<String,Object>> getListPage(Map<String,Object> map);

	/**
	 * getRows
	 * @param map
	 * @return  id desc,name ,date asc
	 */  
	public long getRows(Map<String, Object> map);

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
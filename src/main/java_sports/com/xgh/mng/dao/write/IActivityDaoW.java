package com.xgh.mng.dao.write;

import com.xgh.mng.entity.Activity;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ActivityDao read
 *
 * @author h2y
 *
 * @time:2016-01-23 11:48:18
 *
 * @Email:
 */
@Component
public interface IActivityDaoW {

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
	 * check
     */
	public int checkActivity(Activity activity);
}
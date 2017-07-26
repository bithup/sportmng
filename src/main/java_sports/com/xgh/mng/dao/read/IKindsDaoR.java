package com.xgh.mng.dao.read;

import com.xgh.mng.entity.Kinds;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * KindsDao read
 
 * @author h2y
 
 * time:2015-12-28 10:58:10
 
 * Gmail:
 */
@Component
public interface IKindsDaoR {
	
	/**
	 * get
	 * @return
	 */
	public Kinds get(long id);
	
	/**
	 * getList
	 * @return
	 */
	public List<Kinds> getList(Kinds kinds);
	

	/**
	 * getListPage
	 * 
	 * page,pagesize,key
	 * @return
	 */
	public List<Kinds> getListPage(Map<String, Object> map);
	
	/**
	 * getRows
	 * @param map
	 * @return  id desc,name ,date asc
	 */  
	public long getRows(Map<String, Object> map);
	
	
	public List<Map<String,Object>> getChildTreeData(Map<String, Object> map);
	
	public long isHasChild(long parentId);

	public Kinds getByParentId(long parentId);

	/**
	 *
	 * @param map
     * @return
     */
	public List<Kinds> getChildData(Map<String, Object> map);
}
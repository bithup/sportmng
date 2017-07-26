package com.xgh.mng.dao.write;

import com.xgh.mng.entity.Kinds;
import org.springframework.stereotype.Component;

/**
 * KindsDao read
 
 * @author h2y
 
 * time:2015-12-28 10:58:10
 
 * Gmail:
 */
@Component
public interface IKindsDaoW {

	/**
	 * add
	 */
	public int add(Kinds kinds);
	
	/**
	 * update
	 */
	public int update(Kinds kinds);
	
	/**
	 * delete
	 */
	public int deleteById(long id);
	
	/**
	 * deleteByNid
	 */
	public int deleteByNid(long nid);



}
package com.xgh.mng.dao.read;

import com.xgh.mng.entity.ExtensionAllocation;

import java.util.List;
import java.util.Map;


/**
 * 
 * 推广利益分配表
 * 
 **/
public interface IExtensionAllocationDaoR {



	/**
	 * 
	 * 返回对象
	 * 
	 **/
	public ExtensionAllocation get(Long id);



	/**
	 * 
	 * 返回列表
	 * 
	 **/
	public List<Map<String, Object>> getList(Map<String, Object> map);



	/**
	 * 
	 * 返回实体对象的分页记录
	 * 
	 **/
	public List<ExtensionAllocation> getListPage(Map<String, Object> map);



	/**
	 * 
	 * 返回Map对象的分页记录
	 * 
	 **/
	public List<Map<String, Object>> getListMapPage(Map<String, Object> map);



	/**
	 * 
	 * 返回记录数量
	 * 
	 **/
	public long getRows(Map<String, Object> map);


}

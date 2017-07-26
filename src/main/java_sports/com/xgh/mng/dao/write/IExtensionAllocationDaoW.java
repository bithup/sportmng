package com.xgh.mng.dao.write;


import com.xgh.mng.entity.ExtensionAllocation;

/**
 * 
 * 推广利益分配表
 * 
 **/
public interface IExtensionAllocationDaoW {



	/**
	 * 
	 * 新增
	 * 
	 **/
	public  int add(ExtensionAllocation extensionAllocation);



	/**
	 * 
	 * 更新
	 * 
	 **/
	public  int update(ExtensionAllocation extensionAllocation);



	/**
	 * 
	 * 逻辑删除
	 * 
	 **/
	public  int deleteById(long id);


}

package com.xgh.mng.dao;

import com.xgh.mng.dao.read.IExtensionAllocationDaoR;
import com.xgh.mng.dao.write.IExtensionAllocationDaoW;
import com.xgh.mng.entity.ExtensionAllocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * 
 * 推广利益分配表
 * 
 **/
@Service("extensionAllocationDao")
public class ExtensionAllocationDaoImpl implements IExtensionAllocationDao {


@Autowired
private IExtensionAllocationDaoR extensionAllocationDaoR;

@Autowired
private IExtensionAllocationDaoW extensionAllocationDaoW;


	/**
	 * 
	 * 返回对象
	 * 
	 **/
	public ExtensionAllocation get(Long id){
		return  extensionAllocationDaoR.get(id);
	}



	/**
	 * 
	 * 返回列表
	 * 
	 **/
	public List<Map<String, Object>> getList(Map<String, Object> map){
		return  extensionAllocationDaoR.getList(map);
	}


	/**
	 * 
	 * 返回实体对象的分页记录
	 * 
	 **/
	public List<ExtensionAllocation> getListPage(Map<String, Object> map){
		return  extensionAllocationDaoR.getListPage(map);
	}


	/**
	 * 
	 * 返回Map对象的分页记录
	 * 
	 **/
	public List<Map<String, Object>> getListMapPage(Map<String, Object> map){
		return  extensionAllocationDaoR.getListMapPage(map);
	}


	/**
	 * 
	 * 返回记录数量
	 * 
	 **/
	public long getRows(Map<String, Object> map){
		return  extensionAllocationDaoR.getRows(map);
	}


	/**
	 * 
	 * 新增
	 * 
	 **/
	public  int add(ExtensionAllocation  extensionAllocation){
		return  extensionAllocationDaoW.add(extensionAllocation);
	}


	/**
	 * 
	 * 更新
	 * 
	 **/
	public  int update(ExtensionAllocation  extensionAllocation){
		return  extensionAllocationDaoW.update(extensionAllocation);
	}


	/**
	 * 
	 * 逻辑删除
	 * 
	 **/
	public  int deleteById(long id){
		return  extensionAllocationDaoW.deleteById(id);
	}

}

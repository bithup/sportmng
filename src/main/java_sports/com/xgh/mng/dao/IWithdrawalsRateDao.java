package com.xgh.mng.dao;

import com.xgh.mng.entity.WithdrawalsRate;

import java.util.List;
import java.util.Map;

/**
 * @Author: cbj
 * @Description:
 * @Date: 2017/7/3
 */
public interface IWithdrawalsRateDao {



	/**
	 * 
	 * 返回对象
	 * 
	 **/
	public WithdrawalsRate get(Long id);



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
	public List<WithdrawalsRate> getListPage(Map<String, Object> map);



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



	/**
	 * 
	 * 新增
	 * 
	 **/
	public  int add(WithdrawalsRate withdrawalsRate);



	/**
	 * 
	 * 更新
	 * 
	 **/
	public  int update(WithdrawalsRate withdrawalsRate);



	/**
	 * 
	 * 逻辑删除
	 * 
	 **/
	public  int deleteById(long id);


}

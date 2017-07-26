package com.xgh.mng.dao.write;

import com.xgh.mng.entity.WithdrawalsRate;

public interface IWithdrawalsRateDaoW {



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

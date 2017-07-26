package com.xgh.mng.dao;

import com.xgh.mng.dao.read.IWithdrawalsRateDaoR;
import com.xgh.mng.dao.write.IWithdrawalsRateDaoW;
import com.xgh.mng.entity.WithdrawalsRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * @Author: cbj
 * @Description:
 * @Date: 2017/7/3
 */
@Service("withdrawalsRateDao")
public class WithdrawalsRateDaoImpl implements IWithdrawalsRateDao {


@Autowired
private IWithdrawalsRateDaoR withdrawalsRateDaoR;

@Autowired
private IWithdrawalsRateDaoW withdrawalsRateDaoW;


	/**
	 * 
	 * 返回对象
	 * 
	 **/
	public WithdrawalsRate get(Long id){
		return  withdrawalsRateDaoR.get(id);
	}



	/**
	 * 
	 * 返回列表
	 * 
	 **/
	public List<Map<String, Object>> getList(Map<String, Object> map){
		return  withdrawalsRateDaoR.getList(map);
	}


	/**
	 * 
	 * 返回实体对象的分页记录
	 * 
	 **/
	public List<WithdrawalsRate> getListPage(Map<String, Object> map){
		return  withdrawalsRateDaoR.getListPage(map);
	}


	/**
	 * 
	 * 返回Map对象的分页记录
	 * 
	 **/
	public List<Map<String, Object>> getListMapPage(Map<String, Object> map){
		return  withdrawalsRateDaoR.getListMapPage(map);
	}


	/**
	 * 
	 * 返回记录数量
	 * 
	 **/
	public long getRows(Map<String, Object> map){
		return  withdrawalsRateDaoR.getRows(map);
	}


	/**
	 * 
	 * 新增
	 * 
	 **/
	public  int add(WithdrawalsRate withdrawalsRate){
		return  withdrawalsRateDaoW.add(withdrawalsRate);
	}


	/**
	 * 
	 * 更新
	 * 
	 **/
	public  int update(WithdrawalsRate withdrawalsRate){
		return  withdrawalsRateDaoW.update(withdrawalsRate);
	}


	/**
	 * 
	 * 逻辑删除
	 * 
	 **/
	public  int deleteById(long id){
		return  withdrawalsRateDaoW.deleteById(id);
	}

}

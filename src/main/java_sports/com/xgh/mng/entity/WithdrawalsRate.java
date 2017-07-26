package com.xgh.mng.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: cbj
 * @Description:
 * @Date: 2017/7/3
 */
public class WithdrawalsRate implements Serializable {

	/**主键id**/
	private long id;

	/**提现费计算方式（1：按百分比例  2：按每笔提现金额）**/
	private int withdrawalsType;

	/**按比例计算提现费百分比数值**/
	private BigDecimal withdrawalsPropor;

	/**按每笔金额每笔提现费**/
	private BigDecimal withdrawalsMoney;

	/**启用状态（1：启用  2：未启用）**/
	private int enableStatus;

	/**创建时间**/
	private Date createDate;

	/**修改时间**/
	private Date updateDate;

	/**启用状态（1：正常  2：已删除）**/
	private int status;

	/**扩展字段1**/
	private String data1;

	/**扩展字段2**/
	private String data2;

	/**扩展字段3**/
	private String data3;


	public WithdrawalsRate() { super(); }

	public WithdrawalsRate(long id) {
	 super();
	 this.id=id;
	}

	public WithdrawalsRate(long id, int withdrawalsType, BigDecimal withdrawalsPropor, BigDecimal withdrawalsMoney, int enableStatus, Date createDate, Date updateDate, int status, String data1, String data2, String data3){
		super();
		this.id = id;
		this.withdrawalsType = withdrawalsType;
		this.withdrawalsPropor = withdrawalsPropor;
		this.withdrawalsMoney = withdrawalsMoney;
		this.enableStatus = enableStatus;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.status = status;
		this.data1 = data1;
		this.data2 = data2;
		this.data3 = data3;

	}
	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return this.id;
	}

	public void setWithdrawalsType(Integer withdrawalsType){
		this.withdrawalsType = withdrawalsType;
	}

	public Integer getWithdrawalsType(){
		return this.withdrawalsType;
	}

	public void setWithdrawalsPropor(BigDecimal withdrawalsPropor){
		this.withdrawalsPropor = withdrawalsPropor;
	}

	public BigDecimal getWithdrawalsPropor(){
		return this.withdrawalsPropor;
	}

	public void setWithdrawalsMoney(BigDecimal withdrawalsMoney){
		this.withdrawalsMoney = withdrawalsMoney;
	}

	public BigDecimal getWithdrawalsMoney(){
		return this.withdrawalsMoney;
	}

	public void setEnableStatus(Integer enableStatus){
		this.enableStatus = enableStatus;
	}

	public Integer getEnableStatus(){
		return this.enableStatus;
	}

	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}

	public Date getCreateDate(){
		return this.createDate;
	}

	public void setUpdateDate(Date updateDate){
		this.updateDate = updateDate;
	}

	public Date getUpdateDate(){
		return this.updateDate;
	}

	public void setStatus(Integer status){
		this.status = status;
	}

	public Integer getStatus(){
		return this.status;
	}

	public void setData1(String data1){
		this.data1 = data1;
	}

	public String getData1(){
		return this.data1;
	}

	public void setData2(String data2){
		this.data2 = data2;
	}

	public String getData2(){
		return this.data2;
	}

	public void setData3(String data3){
		this.data3 = data3;
	}

	public String getData3(){
		return this.data3;
	}

}

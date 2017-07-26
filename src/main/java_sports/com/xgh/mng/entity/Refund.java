package com.xgh.mng.entity;
import java.io.Serializable;
import java.util.Date;


/**
 * 
 * 
 * 
 **/
@SuppressWarnings("serial")
public class Refund implements Serializable {

	/**主键id**/
	private long id;

	/**用户id**/
	private long memberId;

	/**订单id**/
	private long orderId;

	/**订单编号**/
	private String orderNo;

	/**退款原因**/
	private String reason;

	/**退款金额**/
	private Double refunndMoney;

	/**退款批次号**/
	private String batchNo;

	/**退款状态（0：未退款 1：成功  2：失败）**/
	private int status;

	/**退款时间**/
	private Date refundTime;

	/****/
	private Date createTime;

	/**修改时间**/
	private Date updateTime;

	/**备用字段1**/
	private String data1;

	/**备用字段2**/
	private long data2;

	/**备用字段3**/
	private int data3;

	/**备用字段4**/
	private Double data4;


	public Refund() { super(); }

	public Refund(long id) {
	 super();
	 this.id=id;
	}

	public Refund(long id,long memberId,long orderId,String orderNo,String reason,Double refunndMoney,String batchNo,int status,Date refundTime,Date createTime,Date updateTime,String data1,long data2,int data3,Double data4){
		super();
		this.id = id;
		this.memberId = memberId;
		this.orderId = orderId;
		this.orderNo = orderNo;
		this.reason = reason;
		this.refunndMoney = refunndMoney;
		this.batchNo = batchNo;
		this.status = status;
		this.refundTime = refundTime;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.data1 = data1;
		this.data2 = data2;
		this.data3 = data3;
		this.data4 = data4;

	}
	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return this.id;
	}

	public void setMemberId(Long memberId){
		this.memberId = memberId;
	}

	public Long getMemberId(){
		return this.memberId;
	}

	public void setOrderId(Long orderId){
		this.orderId = orderId;
	}

	public Long getOrderId(){
		return this.orderId;
	}

	public void setOrderNo(String orderNo){
		this.orderNo = orderNo;
	}

	public String getOrderNo(){
		return this.orderNo;
	}

	public void setReason(String reason){
		this.reason = reason;
	}

	public String getReason(){
		return this.reason;
	}

	public void setRefunndMoney(Double refunndMoney){
		this.refunndMoney = refunndMoney;
	}

	public Double getRefunndMoney(){
		return this.refunndMoney;
	}

	public void setBatchNo(String batchNo){
		this.batchNo = batchNo;
	}

	public String getBatchNo(){
		return this.batchNo;
	}

	public void setStatus(Integer status){
		this.status = status;
	}

	public Integer getStatus(){
		return this.status;
	}

	public void setRefundTime(Date refundTime){
		this.refundTime = refundTime;
	}

	public Date getRefundTime(){
		return this.refundTime;
	}

	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}

	public Date getCreateTime(){
		return this.createTime;
	}

	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}

	public Date getUpdateTime(){
		return this.updateTime;
	}

	public void setData1(String data1){
		this.data1 = data1;
	}

	public String getData1(){
		return this.data1;
	}

	public void setData2(Long data2){
		this.data2 = data2;
	}

	public Long getData2(){
		return this.data2;
	}

	public void setData3(Integer data3){
		this.data3 = data3;
	}

	public Integer getData3(){
		return this.data3;
	}

	public void setData4(Double data4){
		this.data4 = data4;
	}

	public Double getData4(){
		return this.data4;
	}

}

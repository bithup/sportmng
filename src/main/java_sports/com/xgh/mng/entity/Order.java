package com.xgh.mng.entity;
import java.io.Serializable;
import java.util.Date;


/**
 * 
 * 
 * 
 **/
@SuppressWarnings("serial")
public class Order implements Serializable {

	/**主键id**/
	private long id;

	/**订单人id**/
	private long memberId;

	/**订单编号**/
	private String orderNo;

	/**下单操作客户端类型（IOS、安卓、PC）**/
	private String orderOs;

	/**联系人**/
	private String contact;

	/**联系电话**/
	private String telephone;

	/**类型（0：场馆  1：教练  2：活动）**/
	private int orderType;

	/**商品id**/
	private long goodsId;

	/**商品名称**/
	private String goodsName;

	/**订单总金额**/
	private Double orderAmount;

	/**商品开始时间**/
	private Date beginDate;

	/**商品结束时间**/
	private Date endDate;

	/**支付方式（0：支付宝  1：微信  2：银行卡）**/
	private int payAccount;

	/**交易号**/
	private String tradeNo;

	/**交易扩展信息1**/
	private String tradeData1;

	/**交易扩展信息2**/
	private long tradeData2;

	/**交易扩展信息3**/
	private int tradeData3;

	/**付款时间**/
	private Date payTime;

	/**是否已退款（0：未退款  1：已退款）**/
	private int isRefund;

	/**订单状态（0：待支付  1：已支付  2：待评价  3：已完成）**/
	private int orderStatus;

	/**创建时间**/
	private Date createDate;

	/**修改时间**/
	private Date updateDate;

	/**状态（-1：已删除  0：正常  1：未删除）**/
	private int status;

	/**备用字段1**/
	private String data1;

	/**备用字段2**/
	private String data2;

	/**备用字段3**/
	private String data3;

	/**备用字段4**/
	private long data4;

	/**备用字段5**/
	private long data5;

	/**备用字段6**/
	private long data6;

	/**备用字段7**/
	private int data7;

	/**备用字段8**/
	private int data8;

	/**备用字段9**/
	private int data9;

	/**备用字段10**/
	private Double data10;

	/**备用字段11**/
	private Double data11;


	public Order() { super(); }

	public Order(long id) {
	 super();
	 this.id=id;
	}

	public Order(long id,long memberId,String orderNo,String orderOs,String contact,String telephone,int orderType,long goodsId,String goodsName,Double orderAmount,Date beginDate,Date endDate,int payAccount,String tradeNo,String tradeData1,long tradeData2,int tradeData3,Date payTime,int isRefund,int orderStatus,Date createDate,Date updateDate,int status,String data1,String data2,String data3,long data4,long data5,long data6,int data7,int data8,int data9,Double data10,Double data11){
		super();
		this.id = id;
		this.memberId = memberId;
		this.orderNo = orderNo;
		this.orderOs = orderOs;
		this.contact = contact;
		this.telephone = telephone;
		this.orderType = orderType;
		this.goodsId = goodsId;
		this.goodsName = goodsName;
		this.orderAmount = orderAmount;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.payAccount = payAccount;
		this.tradeNo = tradeNo;
		this.tradeData1 = tradeData1;
		this.tradeData2 = tradeData2;
		this.tradeData3 = tradeData3;
		this.payTime = payTime;
		this.isRefund = isRefund;
		this.orderStatus = orderStatus;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.status = status;
		this.data1 = data1;
		this.data2 = data2;
		this.data3 = data3;
		this.data4 = data4;
		this.data5 = data5;
		this.data6 = data6;
		this.data7 = data7;
		this.data8 = data8;
		this.data9 = data9;
		this.data10 = data10;
		this.data11 = data11;

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

	public void setOrderNo(String orderNo){
		this.orderNo = orderNo;
	}

	public String getOrderNo(){
		return this.orderNo;
	}

	public void setOrderOs(String orderOs){
		this.orderOs = orderOs;
	}

	public String getOrderOs(){
		return this.orderOs;
	}

	public void setContact(String contact){
		this.contact = contact;
	}

	public String getContact(){
		return this.contact;
	}

	public void setTelephone(String telephone){
		this.telephone = telephone;
	}

	public String getTelephone(){
		return this.telephone;
	}

	public void setOrderType(Integer orderType){
		this.orderType = orderType;
	}

	public Integer getOrderType(){
		return this.orderType;
	}

	public void setGoodsId(Long goodsId){
		this.goodsId = goodsId;
	}

	public Long getGoodsId(){
		return this.goodsId;
	}

	public void setGoodsName(String goodsName){
		this.goodsName = goodsName;
	}

	public String getGoodsName(){
		return this.goodsName;
	}

	public void setOrderAmount(Double orderAmount){
		this.orderAmount = orderAmount;
	}

	public Double getOrderAmount(){
		return this.orderAmount;
	}

	public void setBeginDate(Date beginDate){
		this.beginDate = beginDate;
	}

	public Date getBeginDate(){
		return this.beginDate;
	}

	public void setEndDate(Date endDate){
		this.endDate = endDate;
	}

	public Date getEndDate(){
		return this.endDate;
	}

	public void setPayAccount(Integer payAccount){
		this.payAccount = payAccount;
	}

	public Integer getPayAccount(){
		return this.payAccount;
	}

	public void setTradeNo(String tradeNo){
		this.tradeNo = tradeNo;
	}

	public String getTradeNo(){
		return this.tradeNo;
	}

	public void setTradeData1(String tradeData1){
		this.tradeData1 = tradeData1;
	}

	public String getTradeData1(){
		return this.tradeData1;
	}

	public void setTradeData2(Long tradeData2){
		this.tradeData2 = tradeData2;
	}

	public Long getTradeData2(){
		return this.tradeData2;
	}

	public void setTradeData3(Integer tradeData3){
		this.tradeData3 = tradeData3;
	}

	public Integer getTradeData3(){
		return this.tradeData3;
	}

	public void setPayTime(Date payTime){
		this.payTime = payTime;
	}

	public Date getPayTime(){
		return this.payTime;
	}

	public void setIsRefund(Integer isRefund){
		this.isRefund = isRefund;
	}

	public Integer getIsRefund(){
		return this.isRefund;
	}

	public void setOrderStatus(Integer orderStatus){
		this.orderStatus = orderStatus;
	}

	public Integer getOrderStatus(){
		return this.orderStatus;
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

	public void setData4(Long data4){
		this.data4 = data4;
	}

	public Long getData4(){
		return this.data4;
	}

	public void setData5(Long data5){
		this.data5 = data5;
	}

	public Long getData5(){
		return this.data5;
	}

	public void setData6(Long data6){
		this.data6 = data6;
	}

	public Long getData6(){
		return this.data6;
	}

	public void setData7(Integer data7){
		this.data7 = data7;
	}

	public Integer getData7(){
		return this.data7;
	}

	public void setData8(Integer data8){
		this.data8 = data8;
	}

	public Integer getData8(){
		return this.data8;
	}

	public void setData9(Integer data9){
		this.data9 = data9;
	}

	public Integer getData9(){
		return this.data9;
	}

	public void setData10(Double data10){
		this.data10 = data10;
	}

	public Double getData10(){
		return this.data10;
	}

	public void setData11(Double data11){
		this.data11 = data11;
	}

	public Double getData11(){
		return this.data11;
	}

}

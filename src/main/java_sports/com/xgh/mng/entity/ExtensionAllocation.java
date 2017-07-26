package com.xgh.mng.entity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 推广利益分配表
 *@Author: cbj
 * * @Description:
 * @Date: 2017/7/4
 **/
@SuppressWarnings("serial")
public class ExtensionAllocation implements Serializable {

	/**主键id**/
	private long id;

	/**推广类型（1：教练   2：活动  3：场馆）**/
	private int extensionType;

	/**分配方式（1：按百分比例  2：按每单金额）**/
	private int allocationType;

	/**按比例分配一级推广百分比数值**/
	private BigDecimal proporLevelOne;

	/**按比例分配二级推广百分比数值**/
	private BigDecimal proporLevelTwo;

	/**按比例分配平台百分比数值**/
	private BigDecimal proporPlatform;

	/**按每单金额分配一级推广每单金额**/
	private BigDecimal orderLevelOne;

	/**按每单金额分配二级推广每单金额**/
	private BigDecimal orderLevelTwo;

	/**按每单金额分配平台每单金额**/
	private BigDecimal orderPlatform;

	/**创建时间**/
	private Date createDate;

	/**修改时间**/
	private Date updateDate;

	/**启用状态（1：启用  2：未启用）**/
	private int enableStatus;

	/**状态（1：正常 2：已删除）**/
	private int status;

	/**扩展字段1**/
	private String data1;

	/**扩展字段2**/
	private String data2;

	/**扩展字段3**/
	private String data3;


	public ExtensionAllocation() { super(); }

	public ExtensionAllocation(long id) {
		super();
		this.id=id;
	}

	public ExtensionAllocation(long id, int extensionType, int allocationType, BigDecimal proporLevelOne, BigDecimal proporLevelTwo, BigDecimal proporPlatform, BigDecimal orderLevelOne, BigDecimal orderLevelTwo, BigDecimal orderPlatform, Date createDate, Date updateDate, int enableStatus, int status, String data1, String data2, String data3){
		super();
		this.id = id;
		this.extensionType = extensionType;
		this.allocationType = allocationType;
		this.proporLevelOne = proporLevelOne;
		this.proporLevelTwo = proporLevelTwo;
		this.proporPlatform = proporPlatform;
		this.orderLevelOne = orderLevelOne;
		this.orderLevelTwo = orderLevelTwo;
		this.orderPlatform = orderPlatform;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.enableStatus = enableStatus;
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

	public void setExtensionType(Integer extensionType){
		this.extensionType = extensionType;
	}

	public Integer getExtensionType(){
		return this.extensionType;
	}

	public void setAllocationType(Integer allocationType){
		this.allocationType = allocationType;
	}

	public Integer getAllocationType(){
		return this.allocationType;
	}

	public void setProporLevelOne(BigDecimal proporLevelOne){
		this.proporLevelOne = proporLevelOne;
	}

	public BigDecimal getProporLevelOne(){
		return this.proporLevelOne;
	}

	public void setProporLevelTwo(BigDecimal proporLevelTwo){
		this.proporLevelTwo = proporLevelTwo;
	}

	public BigDecimal getProporLevelTwo(){
		return this.proporLevelTwo;
	}

	public void setProporPlatform(BigDecimal proporPlatform){
		this.proporPlatform = proporPlatform;
	}

	public BigDecimal getProporPlatform(){
		return this.proporPlatform;
	}

	public void setOrderLevelOne(BigDecimal orderLevelOne){
		this.orderLevelOne = orderLevelOne;
	}

	public BigDecimal getOrderLevelOne(){
		return this.orderLevelOne;
	}

	public void setOrderLevelTwo(BigDecimal orderLevelTwo){
		this.orderLevelTwo = orderLevelTwo;
	}

	public BigDecimal getOrderLevelTwo(){
		return this.orderLevelTwo;
	}

	public void setOrderPlatform(BigDecimal orderPlatform){
		this.orderPlatform = orderPlatform;
	}

	public BigDecimal getOrderPlatform(){
		return this.orderPlatform;
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

	public void setEnableStatus(Integer enableStatus){
		this.enableStatus = enableStatus;
	}

	public Integer getEnableStatus(){
		return this.enableStatus;
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

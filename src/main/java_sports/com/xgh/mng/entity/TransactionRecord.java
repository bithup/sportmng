package com.xgh.mng.entity;
import java.io.Serializable;
import java.util.Date;


/**
 * 
 * 
 * 
 **/
@SuppressWarnings("serial")
public class TransactionRecord implements Serializable {

	/****/
	private long id;

	/**行业id**/
	private long instId;

	/**行业编码**/
	private String instCode;

	/**用户id**/
	private long memberId;

	/**用户类型1，学生，2 场地，3、教师**/
	private int memberType;

	/**交易类型：1.支出，2收入**/
	private int transType;

	/**交易来源：1买课程；2.定场地；3活动；4；提现；5充值**/
	private int sources;

	/**交易金额**/
	private Double transMoney;

	/**交易时间**/
	private Date transTime;

	/**创建时间**/
	private Date createDate;

	/**修改时间**/
	private Date updateDate;

	/**状态：0.正常；**/
	private int status;

	/**备注**/
	private String remark;

	/**备用字段**/
	private String data1;

	/**备用字段**/
	private String data2;

	/**备用字段**/
	private String data3;

	/**备用字段**/
	private Double data4;

	/**备用字段**/
	private int data5;

	/**备用字段**/
	private long data6;


	public TransactionRecord() { super(); }

	public TransactionRecord(long id) {
	 super();
	 this.id=id;
	}

	public TransactionRecord(long id, long instId, String instCode, long memberId, int memberType, int transType, int sources, Double transMoney, Date transTime, Date createDate, Date updateDate, int status, String remark, String data1, String data2, String data3, Double data4, int data5, long data6){
		super();
		this.id = id;
		this.instId = instId;
		this.instCode = instCode;
		this.memberId = memberId;
		this.memberType = memberType;
		this.transType = transType;
		this.sources = sources;
		this.transMoney = transMoney;
		this.transTime = transTime;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.status = status;
		this.remark = remark;
		this.data1 = data1;
		this.data2 = data2;
		this.data3 = data3;
		this.data4 = data4;
		this.data5 = data5;
		this.data6 = data6;

	}
	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return this.id;
	}

	public void setInstId(Long instId){
		this.instId = instId;
	}

	public Long getInstId(){
		return this.instId;
	}

	public void setInstCode(String instCode){
		this.instCode = instCode;
	}

	public String getInstCode(){
		return this.instCode;
	}

	public void setMemberId(Long memberId){
		this.memberId = memberId;
	}

	public Long getMemberId(){
		return this.memberId;
	}

	public void setMemberType(Integer memberType){
		this.memberType = memberType;
	}

	public Integer getMemberType(){
		return this.memberType;
	}

	public void setTransType(Integer transType){
		this.transType = transType;
	}

	public Integer getTransType(){
		return this.transType;
	}

	public void setSources(Integer sources){
		this.sources = sources;
	}

	public Integer getSources(){
		return this.sources;
	}

	public void setTransMoney(Double transMoney){
		this.transMoney = transMoney;
	}

	public Double getTransMoney(){
		return this.transMoney;
	}

	public void setTransTime(Date transTime){
		this.transTime = transTime;
	}

	public Date getTransTime(){
		return this.transTime;
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

	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getRemark(){
		return this.remark;
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

	public void setData4(Double data4){
		this.data4 = data4;
	}

	public Double getData4(){
		return this.data4;
	}

	public void setData5(Integer data5){
		this.data5 = data5;
	}

	public Integer getData5(){
		return this.data5;
	}

	public void setData6(Long data6){
		this.data6 = data6;
	}

	public Long getData6(){
		return this.data6;
	}

}

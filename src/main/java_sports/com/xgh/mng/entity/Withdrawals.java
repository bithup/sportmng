package com.xgh.mng.entity;
import java.io.Serializable;
import java.util.Date;


/**
 * 
 * 提现申请表
 * 
 **/
@SuppressWarnings("serial")
public class Withdrawals implements Serializable {

	/**主键id**/
	private long id;

	/**行业id**/
	private long instId;

	/**行业编码**/
	private String instCode;

	/**代理id**/
	private long unitId;

	/**代理编码**/
	private String unitCode;

	/**用户id**/
	private long memberId;

	/**会员类型，1，学生，2 场地，3、教师**/
	private int memberType;

	/**实提金额**/
	private Double realAmount;

	/**提现金额**/
	private Double cashWithdrawalAmount;

	/**手续费**/
	private Double fee;

	/**姓名**/
	private String name;

	/**身份证号**/
	private String idCard;

	/**手机号**/
	private String mobile;

	/**提现到账户（银行卡或支付宝号）**/
	private String drawAccount;

	/**提现状态（0.待审核；1.已审核(拒绝）；2完成（已打款））**/
	private int drawStatus;

	/**支付时间**/
	private Date payTime;

	/**创建时间**/
	private Date createDate;

	/**修改时间**/
	private Date updateDate;

	/**状态：0.正常；-1删除**/
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
	private long data4;

	/**备用字段**/
	private int data5;

	/**备用字段**/
	private Double data6;


	public Withdrawals() { super(); }

	public Withdrawals(long id) {
	 super();
	 this.id=id;
	}

	public Withdrawals(long id,long instId,String instCode,long unitId,String unitCode,long memberId,int memberType,Double realAmount,Double cashWithdrawalAmount,Double fee,String name,String idCard,String mobile,String drawAccount,int drawStatus,Date payTime,Date createDate,Date updateDate,int status,String remark,String data1,String data2,String data3,long data4,int data5,Double data6){
		super();
		this.id = id;
		this.instId = instId;
		this.instCode = instCode;
		this.unitId = unitId;
		this.unitCode = unitCode;
		this.memberId = memberId;
		this.memberType = memberType;
		this.realAmount = realAmount;
		this.cashWithdrawalAmount = cashWithdrawalAmount;
		this.fee = fee;
		this.name = name;
		this.idCard = idCard;
		this.mobile = mobile;
		this.drawAccount = drawAccount;
		this.drawStatus = drawStatus;
		this.payTime = payTime;
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

	public void setUnitId(Long unitId){
		this.unitId = unitId;
	}

	public Long getUnitId(){
		return this.unitId;
	}

	public void setUnitCode(String unitCode){
		this.unitCode = unitCode;
	}

	public String getUnitCode(){
		return this.unitCode;
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

	public void setRealAmount(Double realAmount){
		this.realAmount = realAmount;
	}

	public Double getRealAmount(){
		return this.realAmount;
	}

	public void setCashWithdrawalAmount(Double cashWithdrawalAmount){
		this.cashWithdrawalAmount = cashWithdrawalAmount;
	}

	public Double getCashWithdrawalAmount(){
		return this.cashWithdrawalAmount;
	}

	public void setFee(Double fee){
		this.fee = fee;
	}

	public Double getFee(){
		return this.fee;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}

	public void setIdCard(String idCard){
		this.idCard = idCard;
	}

	public String getIdCard(){
		return this.idCard;
	}

	public void setMobile(String mobile){
		this.mobile = mobile;
	}

	public String getMobile(){
		return this.mobile;
	}

	public void setDrawAccount(String drawAccount){
		this.drawAccount = drawAccount;
	}

	public String getDrawAccount(){
		return this.drawAccount;
	}

	public void setDrawStatus(Integer drawStatus){
		this.drawStatus = drawStatus;
	}

	public Integer getDrawStatus(){
		return this.drawStatus;
	}

	public void setPayTime(Date payTime){
		this.payTime = payTime;
	}

	public Date getPayTime(){
		return this.payTime;
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

	public void setData4(Long data4){
		this.data4 = data4;
	}

	public Long getData4(){
		return this.data4;
	}

	public void setData5(Integer data5){
		this.data5 = data5;
	}

	public Integer getData5(){
		return this.data5;
	}

	public void setData6(Double data6){
		this.data6 = data6;
	}

	public Double getData6(){
		return this.data6;
	}

}

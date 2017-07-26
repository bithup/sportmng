package com.xgh.mng.entity;
import java.io.Serializable;
import java.util.Date;


/**
 * 
 * 
 * 
 **/
@SuppressWarnings("serial")
public class Venue implements Serializable {

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

	/**场馆名称**/
	private String name;

	/**场馆详细地址**/
	private String address;

	/**场馆区域id**/
	private long zoneId;

	/**场馆所属区域（省市区）**/
	private String zoneName;

	/**场馆区域编码**/
	private String zoneCode;

	/**联系电话**/
	private String telephone;

	/**移动电话**/
	private String mobile;

	/**联系人**/
	private String contact;

	/**联系人性别（0：男  1：女）**/
	private int sex;

	/**场馆简介**/
	private String introduction;

	/**营业时间**/
	private String businessTime;

	/**营业起始时间**/
	private String startTime;

	/**营业截止时间**/
	private String endTime;

	/**营业执照编号**/
	private String licenseNo;

	/**法人代表**/
	private String artificilPerson;

	/**组成形式**/
	private String organizationType;

	/**营业执照存储路径**/
	private String licenseUrl;

	/**场馆主图存储路径**/
	private String pictureUrl;

	/**场馆主图存储绝对路径**/
	private String picRealPath;

	/**经营者身份证正面照路径**/
	private String idCardFront;

	/**经营者身份证背面照路径**/
	private String idCardBack;

	/**公交交通信息**/
	private String busInfo;

	/**地铁交通信息**/
	private String subwayInfo;

	/**服务信息（如免费WiFi，免费停车）**/
	private String serviceInfo;

	/**硬件设备信息（如多少个跑步机、游泳池）**/
	private String hardware;

	/**是否通过审核（0：未审核  1：通过  2：未通过）**/
	private int isCheck;

	/**是否可退款（0：不能  1：可以）**/
	private int isRefund;

	/**是否是热门场馆（0：不是  1：是）**/
	private int isRecommend;

	/**退款截止日期，以小时为单位**/
	private int refundDeadline;

	/**创建时间**/
	private Date createDate;

	/**修改时间**/
	private Date updateDate;

	/**状态（-1：已删除  0：正常  1：未删除）**/
	private int status;

	/**排序**/
	private long ord;

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


	public Venue() { super(); }

	public Venue(long id) {
	 super();
	 this.id=id;
	}

	public Venue(long id,long instId,String instCode,long unitId,String unitCode,String name,String address,long zoneId,String zoneName,String zoneCode,String telephone,String mobile,String contact,int sex,String introduction,String businessTime,String startTime,String endTime,String licenseNo,String artificilPerson,String organizationType,String licenseUrl,String pictureUrl,String picRealPath,String idCardFront,String idCardBack,String busInfo,String subwayInfo,String serviceInfo,String hardware,int isCheck,int isRefund,int isRecommend,int refundDeadline,Date createDate,Date updateDate,int status,long ord,String data1,String data2,String data3,long data4,long data5,long data6,int data7,int data8,int data9,Double data10,Double data11){
		super();
		this.id = id;
		this.instId = instId;
		this.instCode = instCode;
		this.unitId = unitId;
		this.unitCode = unitCode;
		this.name = name;
		this.address = address;
		this.zoneId = zoneId;
		this.zoneName = zoneName;
		this.zoneCode = zoneCode;
		this.telephone = telephone;
		this.mobile = mobile;
		this.contact = contact;
		this.sex = sex;
		this.introduction = introduction;
		this.businessTime = businessTime;
		this.startTime = startTime;
		this.endTime = endTime;
		this.licenseNo = licenseNo;
		this.artificilPerson = artificilPerson;
		this.organizationType = organizationType;
		this.licenseUrl = licenseUrl;
		this.pictureUrl = pictureUrl;
		this.picRealPath = picRealPath;
		this.idCardFront = idCardFront;
		this.idCardBack = idCardBack;
		this.busInfo = busInfo;
		this.subwayInfo = subwayInfo;
		this.serviceInfo = serviceInfo;
		this.hardware = hardware;
		this.isCheck = isCheck;
		this.isRefund = isRefund;
		this.isRecommend = isRecommend;
		this.refundDeadline = refundDeadline;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.status = status;
		this.ord = ord;
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

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return this.address;
	}

	public void setZoneId(Long zoneId){
		this.zoneId = zoneId;
	}

	public Long getZoneId(){
		return this.zoneId;
	}

	public void setZoneName(String zoneName){
		this.zoneName = zoneName;
	}

	public String getZoneName(){
		return this.zoneName;
	}

	public void setZoneCode(String zoneCode){
		this.zoneCode = zoneCode;
	}

	public String getZoneCode(){
		return this.zoneCode;
	}

	public void setTelephone(String telephone){
		this.telephone = telephone;
	}

	public String getTelephone(){
		return this.telephone;
	}

	public void setMobile(String mobile){
		this.mobile = mobile;
	}

	public String getMobile(){
		return this.mobile;
	}

	public void setContact(String contact){
		this.contact = contact;
	}

	public String getContact(){
		return this.contact;
	}

	public void setSex(Integer sex){
		this.sex = sex;
	}

	public Integer getSex(){
		return this.sex;
	}

	public void setIntroduction(String introduction){
		this.introduction = introduction;
	}

	public String getIntroduction(){
		return this.introduction;
	}

	public void setBusinessTime(String businessTime){
		this.businessTime = businessTime;
	}

	public String getBusinessTime(){
		return this.businessTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public void setLicenseNo(String licenseNo){
		this.licenseNo = licenseNo;
	}

	public String getLicenseNo(){
		return this.licenseNo;
	}

	public void setArtificilPerson(String artificilPerson){
		this.artificilPerson = artificilPerson;
	}

	public String getArtificilPerson(){
		return this.artificilPerson;
	}

	public void setOrganizationType(String organizationType){
		this.organizationType = organizationType;
	}

	public String getOrganizationType(){
		return this.organizationType;
	}

	public void setLicenseUrl(String licenseUrl){
		this.licenseUrl = licenseUrl;
	}

	public String getLicenseUrl(){
		return this.licenseUrl;
	}

	public void setPictureUrl(String pictureUrl){
		this.pictureUrl = pictureUrl;
	}

	public String getPictureUrl(){
		return this.pictureUrl;
	}

	public void setPicRealPath(String picRealPath){
		this.picRealPath = picRealPath;
	}

	public String getPicRealPath(){
		return this.picRealPath;
	}

	public void setIdCardFront(String idCardFront){
		this.idCardFront = idCardFront;
	}

	public String getIdCardFront(){
		return this.idCardFront;
	}

	public void setIdCardBack(String idCardBack){
		this.idCardBack = idCardBack;
	}

	public String getIdCardBack(){
		return this.idCardBack;
	}

	public void setBusInfo(String busInfo){
		this.busInfo = busInfo;
	}

	public String getBusInfo(){
		return this.busInfo;
	}

	public void setSubwayInfo(String subwayInfo){
		this.subwayInfo = subwayInfo;
	}

	public String getSubwayInfo(){
		return this.subwayInfo;
	}

	public void setServiceInfo(String serviceInfo){
		this.serviceInfo = serviceInfo;
	}

	public String getServiceInfo(){
		return this.serviceInfo;
	}

	public void setHardware(String hardware){
		this.hardware = hardware;
	}

	public String getHardware(){
		return this.hardware;
	}

	public void setIsCheck(Integer isCheck){
		this.isCheck = isCheck;
	}

	public Integer getIsCheck(){
		return this.isCheck;
	}

	public void setIsRefund(Integer isRefund){
		this.isRefund = isRefund;
	}

	public Integer getIsRefund(){
		return this.isRefund;
	}

	public void setIsRecommend(Integer isRecommend){
		this.isRecommend = isRecommend;
	}

	public Integer getIsRecommend(){
		return this.isRecommend;
	}

	public void setRefundDeadline(Integer refundDeadline){
		this.refundDeadline = refundDeadline;
	}

	public Integer getRefundDeadline(){
		return this.refundDeadline;
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

	public void setOrd(Long ord){
		this.ord = ord;
	}

	public Long getOrd(){
		return this.ord;
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

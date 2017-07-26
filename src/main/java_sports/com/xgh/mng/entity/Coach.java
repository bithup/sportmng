package com.xgh.mng.entity;
import java.io.Serializable;
import java.util.Date;


/**
 *
 *
 *
 **/
@SuppressWarnings("serial")
public class Coach implements Serializable {

	/**主键id**/
	private long id;

	/**教练名称**/
	private String name;

	/**性别**/
	private int sex=0;

	/**生日**/
	private Date birthday;

	/**身高**/
	private Double height=0.0;

	/**体重**/
	private Double weight=0.0;

	/**电话**/
	private String telPhone;

	/**教学经历**/
	private String teachingCareer;

	/**常驻场馆id**/
	private long venueId;

	/**常驻场馆名称（包含地址和场馆名）**/
	private String venueName;

	/**运动类型id**/
	private long sportId;

	/**教练简介**/
	private String intro;

	/**可教时间，相当于营业时间**/
	private String businessTime;

	/**身份证正面照路径**/
	private String idCardFront;

	/**身份证背面照路径**/
	private String idCardBack;

	/**是否认证（0：未认证  1：已认证）**/
	private int isTrue=0;

	/**头像路径**/
	private String headPath;

	/**头像绝对路径**/
	private String headRealPath;

	/**是否通过审核（0：未审核  1：通过  2：未通过）**/
	private int isCheck=0;

	/**是否是热门教练（0：不是  1：是）**/
	private int isRecommend=0;

	/**状态（-1：已删除  0：正常  1：未删除）**/
	private int status=0;

	/**创建时间**/
	private Date createDate;

	/**修改时间**/
	private Date updateDate;

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


	public Coach() { super(); }

	public Coach(long id) {
		super();
		this.id=id;
	}

	public Coach(long id,String name,int sex,Date birthday,Double height,Double weight,String telPhone,String teachingCareer,long venueId,String venueName,long sportId,String intro,String businessTime,String idCardFront,String idCardBack,int isTrue,String headPath,String headRealPath,int isCheck,int isRecommend,int status,Date createDate,Date updateDate,String data1,String data2,String data3,long data4,long data5,long data6,int data7,int data8,int data9,Double data10,Double data11){
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.birthday = birthday;
		this.height = height;
		this.weight = weight;
		this.telPhone = telPhone;
		this.teachingCareer = teachingCareer;
		this.venueId = venueId;
		this.venueName = venueName;
		this.sportId = sportId;
		this.intro = intro;
		this.businessTime = businessTime;
		this.idCardFront = idCardFront;
		this.idCardBack = idCardBack;
		this.isTrue = isTrue;
		this.headPath = headPath;
		this.headRealPath = headRealPath;
		this.isCheck = isCheck;
		this.isRecommend = isRecommend;
		this.status = status;
		this.createDate = createDate;
		this.updateDate = updateDate;
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

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}

	public void setSex(Integer sex){
		this.sex = sex;
	}

	public Integer getSex(){
		return this.sex;
	}

	public void setBirthday(Date birthday){
		this.birthday = birthday;
	}

	public Date getBirthday(){
		return this.birthday;
	}

	public void setHeight(Double height){
		this.height = height;
	}

	public Double getHeight(){
		return this.height;
	}

	public void setWeight(Double weight){
		this.weight = weight;
	}

	public Double getWeight(){
		return this.weight;
	}

	public void setTelPhone(String telPhone){
		this.telPhone = telPhone;
	}

	public String getTelPhone(){
		return this.telPhone;
	}

	public void setTeachingCareer(String teachingCareer){
		this.teachingCareer = teachingCareer;
	}

	public String getTeachingCareer(){
		return this.teachingCareer;
	}

	public void setVenueId(Long venueId){
		this.venueId = venueId;
	}

	public Long getVenueId(){
		return this.venueId;
	}

	public void setVenueName(String venueName){
		this.venueName = venueName;
	}

	public String getVenueName(){
		return this.venueName;
	}

	public void setSportId(Long sportId){
		this.sportId = sportId;
	}

	public Long getSportId(){
		return this.sportId;
	}

	public void setIntro(String intro){
		this.intro = intro;
	}

	public String getIntro(){
		return this.intro;
	}

	public void setBusinessTime(String businessTime){
		this.businessTime = businessTime;
	}

	public String getBusinessTime(){
		return this.businessTime;
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

	public void setIsTrue(Integer isTrue){
		this.isTrue = isTrue;
	}

	public Integer getIsTrue(){
		return this.isTrue;
	}

	public void setHeadPath(String headPath){
		this.headPath = headPath;
	}

	public String getHeadPath(){
		return this.headPath;
	}

	public void setHeadRealPath(String headRealPath){
		this.headRealPath = headRealPath;
	}

	public String getHeadRealPath(){
		return this.headRealPath;
	}

	public void setIsCheck(Integer isCheck){
		this.isCheck = isCheck;
	}

	public Integer getIsCheck(){
		return this.isCheck;
	}

	public void setIsRecommend(Integer isRecommend){
		this.isRecommend = isRecommend;
	}

	public Integer getIsRecommend(){
		return this.isRecommend;
	}

	public void setStatus(Integer status){
		this.status = status;
	}

	public Integer getStatus(){
		return this.status;
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

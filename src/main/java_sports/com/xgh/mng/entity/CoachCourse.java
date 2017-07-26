package com.xgh.mng.entity;
import java.io.Serializable;
import java.util.Date;


/**
 * 
 * 
 * 
 **/
@SuppressWarnings("serial")
public class CoachCourse implements Serializable {

	/**主键id**/
	private long id;

	/**教练id**/
	private long coachId;

	/**课程类型（1：月份  2：季份  3：年份）**/
	private int courseType=0;

	/**课程名称**/
	private String courseName;

	/**课程介绍**/
	private String introduct;

	/**上课时间**/
	private String courseTime;

	/**单价**/
	private Double price=0.0;

	/**优惠价**/
	private Double salesPrice=0.0;

	/**开始时间**/
	private String  startDate;

	/**结束时间**/
	private String endDate;

	/**主图存储路径**/
	private String picPath="";

	/**主图存储绝对路径**/
	private String picRealPath="";

	/**状态（-1：已删除  0：可预约  1：过期）**/
	private int status;

	/**创建时间**/
	private Date createDate;

	/**修改时间**/
	private Date updateDate;

	/**备用字段1**/
	private String data1;

	/**备用字段2**/
	private String data2;

	/**备用字段3**/
	private long data3;

	/**备用字段4**/
	private long data4;

	/**备用字段5**/
	private int data5;

	/**备用字段6**/
	private int data6;

	/**备用字段7**/
	private Double data7;

	/**备用字段8**/
	private Double data8;


	public CoachCourse() { super(); }

	public CoachCourse(long id) {
	 super();
	 this.id=id;
	}

	public CoachCourse(long id,long coachId,int courseType,String courseName,String introduct,String courseTime,Double price,Double salesPrice,String startDate,String endDate,String picPath,String picRealPath,int status,Date createDate,Date updateDate,String data1,String data2,long data3,long data4,int data5,int data6,Double data7,Double data8){
		super();
		this.id = id;
		this.coachId = coachId;
		this.courseType = courseType;
		this.courseName = courseName;
		this.introduct = introduct;
		this.courseTime = courseTime;
		this.price = price;
		this.salesPrice = salesPrice;
		this.startDate = startDate;
		this.endDate = endDate;
		this.picPath = picPath;
		this.picRealPath = picRealPath;
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

	}
	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return this.id;
	}

	public void setCoachId(Long coachId){
		this.coachId = coachId;
	}

	public Long getCoachId(){
		return this.coachId;
	}

	public void setCourseType(Integer courseType){
		this.courseType = courseType;
	}

	public Integer getCourseType(){
		return this.courseType;
	}

	public void setCourseName(String courseName){
		this.courseName = courseName;
	}

	public String getCourseName(){
		return this.courseName;
	}

	public void setIntroduct(String introduct){
		this.introduct = introduct;
	}

	public String getIntroduct(){
		return this.introduct;
	}

	public void setCourseTime(String courseTime){
		this.courseTime = courseTime;
	}

	public String getCourseTime(){
		return this.courseTime;
	}

	public void setPrice(Double price){
		this.price = price;
	}

	public Double getPrice(){
		return this.price;
	}

	public void setSalesPrice(Double salesPrice){
		this.salesPrice = salesPrice;
	}

	public Double getSalesPrice(){
		return this.salesPrice;
	}

	public void setStartDate(String startDate){
		this.startDate = startDate;
	}

	public String getStartDate(){
		return this.startDate;
	}

	public void setEndDate(String endDate){
		this.endDate = endDate;
	}

	public String getEndDate(){
		return this.endDate;
	}

	public void setPicPath(String picPath){
		this.picPath = picPath;
	}

	public String getPicPath(){
		return this.picPath;
	}

	public void setPicRealPath(String picRealPath){
		this.picRealPath = picRealPath;
	}

	public String getPicRealPath(){
		return this.picRealPath;
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

	public void setData3(Long data3){
		this.data3 = data3;
	}

	public Long getData3(){
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

	public void setData6(Integer data6){
		this.data6 = data6;
	}

	public Integer getData6(){
		return this.data6;
	}

	public void setData7(Double data7){
		this.data7 = data7;
	}

	public Double getData7(){
		return this.data7;
	}

	public void setData8(Double data8){
		this.data8 = data8;
	}

	public Double getData8(){
		return this.data8;
	}

}

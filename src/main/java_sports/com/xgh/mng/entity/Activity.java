package com.xgh.mng.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Activity 
 *
 * @author h2y
 *
 * @time:2016-11-01
 *
 * @Email:
 */

public class Activity implements Serializable{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;
    public static final String key = "keyActivity";
	//
    private long id;
	//代理id
    private long unitId;
	//创建人id
    private long userId;
	//活动名称
    private String activityName;
    //活动类型（0：个人 1：团体）
    private int activityType;
    //活动主办方
    private String activityOrganizer;
    //活动人数
    private int activityCount;
    //活动地点
    private String activityAddress;
    //活动运动项id
    private long sportId;
    //活动场馆id
    private long venueId;
    //活动费用
    private double activityPrice;
    //活动介绍
    private String activityIntroduce;
    //活动联系人
    private String activityContacts;
    //活动联系人电话
    private String contactsPhone;
    //活动图片路径
    private String activityPath;
    //活动图片绝对路径
    private String activityRealPath;
    //活动申明
    private String declares;
    //是否加入热门活动（0：否 1：是）
    private int isRecommend=0;
    //收费方式（0：收费 1：免费 2：AA制）
    private int isFree;
    //活动开始日期
    private Date startDate;
    //活动结束日期
    private Date endDate;
    //活动报名截止日期
    private Date enrollDate;
    //是否通过审核（0：未审核 1：通过 2：未通过）
    private int isCheck=0;
	//创建时间
    private Date createDate;
	//更新时间
    private Date updateDate;
	//活动排序
    private int ord;
	//-1：已删除 1：未删除
    private int status = 1;
    private long data1;
    private long data2;
    private long data3;
    private double data4;
    private double data5;
    private int data6;
    private int data7;
    private int data8;
    private String data9;
    private String data10;
    private String data11;

	public Activity(){
		super();
	}

	public Activity(long id){
		super();
		this.id = id;
	}

    public Activity(long id, long unitId, long userId, String activityName, int activityType, String activityOrganizer, int activityCount,String activityAddress, long sportId,long venueId, double activityPrice,String activityIntroduce, String activityContacts,String contactsPhone, String activityPath,String activityRealPath, String declares,int isRecommend, int isFree,int isCheck, Date startDate, Date endDate, Date enrollDate, Date createDate, Date updateDate, int ord, int status, long data1, long data2, long data3, double data4, double data5, int data6, int data7, int data8, String data9, String data10,String data11) {
        this.id = id;
        this.unitId = unitId;
        this.userId = userId;
        this.activityName = activityName;
        this.activityType = activityType;
        this.activityOrganizer = activityOrganizer;
        this.activityCount = activityCount;
        this.activityAddress = activityAddress;
        this.sportId = sportId;
        this.venueId = venueId;
        this.activityPrice = activityPrice;
        this.activityIntroduce = activityIntroduce;
        this.activityContacts = activityContacts;
        this.contactsPhone = contactsPhone;
        this.activityPath = activityPath;
        this.activityRealPath = activityRealPath;
        this.declares = declares;
        this.isRecommend = isRecommend;
        this.isFree = isFree;
        this.isCheck = isCheck;
        this.startDate = startDate;
        this.endDate = endDate;
        this.enrollDate = enrollDate;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.ord = ord;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUnitId() {
        return unitId;
    }

    public void setUnitId(long unitId) {
        this.unitId = unitId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public int getActivityType() {
        return activityType;
    }

    public void setActivityType(int activityType) {
        this.activityType = activityType;
    }

    public String getActivityOrganizer() {
        return activityOrganizer;
    }

    public void setActivityOrganizer(String activityOrganizer) {
        this.activityOrganizer = activityOrganizer;
    }

    public int getActivityCount() {
        return activityCount;
    }

    public void setActivityCount(int activityCount) {
        this.activityCount = activityCount;
    }

    public String getActivityAddress() {
        return activityAddress;
    }

    public void setActivityAddress(String activityAddress) {
        this.activityAddress = activityAddress;
    }

    public long getSportId() {
        return sportId;
    }

    public void setSportId(long sportId) {
        this.sportId = sportId;
    }

    public long getVenueId() {
        return venueId;
    }

    public void setVenueId(long venueId) {
        this.venueId = venueId;
    }

    public double getActivityPrice() {
        return activityPrice;
    }

    public void setActivityPrice(double activityPrice) {
        this.activityPrice = activityPrice;
    }

    public String getActivityIntroduce() {
        return activityIntroduce;
    }

    public void setActivityIntroduce(String activityIntroduce) {
        this.activityIntroduce = activityIntroduce;
    }

    public String getActivityContacts() {
        return activityContacts;
    }

    public void setActivityContacts(String activityContacts) {
        this.activityContacts = activityContacts;
    }

    public String getContactsPhone() {
        return contactsPhone;
    }

    public void setContactsPhone(String contactsPhone) {
        this.contactsPhone = contactsPhone;
    }

    public String getActivityPath() {
        return activityPath;
    }

    public void setActivityPath(String activityPath) {
        this.activityPath = activityPath;
    }

    public String getActivityRealPath() {
        return activityRealPath;
    }

    public void setActivityRealPath(String activityRealPath) {
        this.activityRealPath = activityRealPath;
    }

    public String getDeclares() {
        return declares;
    }

    public void setDeclares(String declares) {
        this.declares = declares;
    }

    public int getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(int isRecommend) {
        this.isRecommend = isRecommend;
    }

    public int getIsFree() {
        return isFree;
    }

    public void setIsFree(int isFree) {
        this.isFree = isFree;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(Date enrollDate) {
        this.enrollDate = enrollDate;
    }

    public int getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(int isCheck) {
        this.isCheck = isCheck;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public int getOrd() {
        return ord;
    }

    public void setOrd(int ord) {
        this.ord = ord;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getData1() {
        return data1;
    }

    public void setData1(long data1) {
        this.data1 = data1;
    }

    public long getData2() {
        return data2;
    }

    public void setData2(long data2) {
        this.data2 = data2;
    }

    public long getData3() {
        return data3;
    }

    public void setData3(long data3) {
        this.data3 = data3;
    }

    public double getData4() {
        return data4;
    }

    public void setData4(double data4) {
        this.data4 = data4;
    }

    public double getData5() {
        return data5;
    }

    public void setData5(double data5) {
        this.data5 = data5;
    }

    public int getData6() {
        return data6;
    }

    public void setData6(int data6) {
        this.data6 = data6;
    }

    public int getData7() {
        return data7;
    }

    public void setData7(int data7) {
        this.data7 = data7;
    }

    public int getData8() {
        return data8;
    }

    public void setData8(int data8) {
        this.data8 = data8;
    }

    public String getData9() {
        return data9;
    }

    public void setData9(String data9) {
        this.data9 = data9;
    }

    public String getData10() {
        return data10;
    }

    public void setData10(String data10) {
        this.data10 = data10;
    }

    public String getData11() {
        return data11;
    }

    public void setData11(String data11) {
        this.data11 = data11;
    }

//    @Override
//    public String toString() {
//        return "Activity{" +
//                "id=" + id +'\'' +
//                ", unitId=" + unitId +'\'' +
//                ", userId=" + userId +'\'' +
//                ", activityName='" + activityName + '\'' +
//                ", activityType='" + activityType+ '\'' +
//                ", activityOrganizer='" + activityOrganizer+ '\'' +
//                ", activityCount='" + activityCount+ '\'' +
//                ", activityAddress='" + activityAddress+ '\'' +
//                ", sportId='" + sportId+ '\'' +
//                ", venueId='" + venueId+ '\'' +
//                ", activityPrice='" + activityPrice+ '\'' +
//                ", activityIntroduce='" + activityIntroduce+ '\'' +
//                ", activityContacts='" + activityContacts+ '\'' +
//                ", contactsPhone='" + contactsPhone+ '\'' +
//                ", activityPath=" + activityPath +'\'' +
//                ", activityRealPath=" + activityRealPath +'\'' +
//                ", declares=" + declares +'\'' +
//                ", isRecommend=" + isRecommend +'\'' +
//                ", isFree=" + isFree +'\'' +
//                ", isCheck=" + isCheck +'\'' +
//                ", startDate=" + startDate +'\'' +
//                ", endDate=" + endDate +'\'' +
//                ", enrollDate=" + enrollDate +'\'' +
//                ", createDate=" + createDate +'\'' +
//                ", updateDate=" + updateDate +'\'' +
//                ", ord=" + ord +'\'' +
//                ", status=" + status +'\'' +
//                ", data1=" + data1 +'\'' +
//                ", data2=" + data2 +'\'' +
//                ", data3=" + data3 +'\'' +
//                ", data4=" + data4 +'\'' +
//                ", data5=" + data5 +'\'' +
//                ", data6=" + data6 +'\'' +
//                ", data7='" + data7 + '\'' +
//                ", data8='" + data8 + '\'' +
//                ", data9='" + data9 + '\'' +
//                ", data10='" + data10 + '\'' +
//                ", data11='" + data11 + '\'' +
//                '}';
 //   }
}
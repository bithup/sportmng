package com.xgh.mng.entity;
import java.io.Serializable;
import java.util.Date;


/**
 * 
 * 
 * 
 **/
@SuppressWarnings("serial")
public class ChildVenue implements Serializable {

	/**主键id**/
	private long id;

	/**分场馆名称**/
	private String venueName;

	/**运动类型id**/
	private long sportId;

	/**场馆编号**/
	private String venueNo;

	/**所属场馆id**/
	private long parentId;

	/**主图存储路径**/
	private String pictureUrl;

	/**主图存储绝对路径**/
	private String picRealPath;

	/**单价**/
	private Double price;

	/**优惠价**/
	private Double salesPrice;

	/**计量单位（1小时、1天等）**/
	private String unit;

	/**分场馆服务**/
	private String serviceInfo;

	/**小提示**/
	private String tips;

	/**排序**/
	private long ord;

	/**场馆设备**/
	private String facility;

	/**是否是热门场馆（0：不是  1：是）**/
	private int isRecommend;

	/**容纳人数（**最大人数）**/
	private int capacity;

	/**状态（-1:已删除   0：正常  1:未删除）**/
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


	public ChildVenue() { super(); }

	public ChildVenue(long id) {
	 super();
	 this.id=id;
	}

	public ChildVenue(long id,String venueName,long sportId,String venueNo,long parentId,String pictureUrl,String picRealPath,Double price,Double salesPrice,String unit,String serviceInfo,String tips,long ord,String facility,int isRecommend,int capacity,int status,Date createDate,Date updateDate,String data1,String data2,long data3,long data4,int data5,int data6,Double data7,Double data8){
		super();
		this.id = id;
		this.venueName = venueName;
		this.sportId = sportId;
		this.venueNo = venueNo;
		this.parentId = parentId;
		this.pictureUrl = pictureUrl;
		this.picRealPath = picRealPath;
		this.price = price;
		this.salesPrice = salesPrice;
		this.unit = unit;
		this.serviceInfo = serviceInfo;
		this.tips = tips;
		this.ord = ord;
		this.facility = facility;
		this.isRecommend = isRecommend;
		this.capacity = capacity;
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

	public void setVenueNo(String venueNo){
		this.venueNo = venueNo;
	}

	public String getVenueNo(){
		return this.venueNo;
	}

	public void setParentId(Long parentId){
		this.parentId = parentId;
	}

	public Long getParentId(){
		return this.parentId;
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

	public void setUnit(String unit){
		this.unit = unit;
	}

	public String getUnit(){
		return this.unit;
	}

	public void setServiceInfo(String serviceInfo){
		this.serviceInfo = serviceInfo;
	}

	public String getServiceInfo(){
		return this.serviceInfo;
	}

	public void setTips(String tips){
		this.tips = tips;
	}

	public String getTips(){
		return this.tips;
	}

	public void setOrd(Long ord){
		this.ord = ord;
	}

	public Long getOrd(){
		return this.ord;
	}

	public void setFacility(String facility){
		this.facility = facility;
	}

	public String getFacility(){
		return this.facility;
	}

	public void setIsRecommend(Integer isRecommend){
		this.isRecommend = isRecommend;
	}

	public Integer getIsRecommend(){
		return this.isRecommend;
	}

	public void setCapacity(Integer capacity){
		this.capacity = capacity;
	}

	public Integer getCapacity(){
		return this.capacity;
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

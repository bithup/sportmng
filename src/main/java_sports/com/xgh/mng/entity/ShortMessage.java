package com.xgh.mng.entity;
import java.io.Serializable;
import java.util.Date;


/**
 * 
 * 
 * 
 **/
@SuppressWarnings("serial")
public class ShortMessage implements Serializable {

	/**主键id**/
	private long id;

	/**手机号码**/
	private String phoneNum;

	/**短信内容**/
	private String content;

	/**发送状态（0：失败  1：成功  2：未知）**/
	private int satatus;

	/**发送时间**/
	private Date sendTime;

	/**备用字段1**/
	private String data1;

	/**备用字段2**/
	private long data2;

	/**备用字段3**/
	private int data3;

	/**备用字段4**/
	private Double data4;


	public ShortMessage() { super(); }

	public ShortMessage(long id) {
	 super();
	 this.id=id;
	}

	public ShortMessage(long id,String phoneNum,String content,int satatus,Date sendTime,String data1,long data2,int data3,Double data4){
		super();
		this.id = id;
		this.phoneNum = phoneNum;
		this.content = content;
		this.satatus = satatus;
		this.sendTime = sendTime;
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

	public void setPhoneNum(String phoneNum){
		this.phoneNum = phoneNum;
	}

	public String getPhoneNum(){
		return this.phoneNum;
	}

	public void setContent(String content){
		this.content = content;
	}

	public String getContent(){
		return this.content;
	}

	public void setSatatus(Integer satatus){
		this.satatus = satatus;
	}

	public Integer getSatatus(){
		return this.satatus;
	}

	public void setSendTime(Date sendTime){
		this.sendTime = sendTime;
	}

	public Date getSendTime(){
		return this.sendTime;
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

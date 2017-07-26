package com.xgh.mng.entity;
import java.io.Serializable;
import java.util.Date;


/**
 * 
 * 
 * 
 **/
@SuppressWarnings("serial")
public class Recommend implements Serializable {

	/**主键id**/
	private long id;

	/**会员id**/
	private long memberId;

	/**类型（1：创意  2：挑毛病  3：吐槽）**/
	private int kind;

	/**内容**/
	private String context;

	/**回复**/
	private String remart;

	/**状态（-1：已删除  0：正常  1：未删除）**/
	private int status;

	/**创建时间**/
	private Date createDate;

	/**修改时间**/
	private Date updateDate;

	/**是否采纳（0：未采纳  1：已采纳）**/
	private int abopt;

	/**备用字段1**/
	private String data1;

	/**备用字段2**/
	private long data2;

	/**备用字段3**/
	private int data3;

	/**备用字段4**/
	private Double data4;


	public Recommend() { super(); }

	public Recommend(long id) {
	 super();
	 this.id=id;
	}

	public Recommend(long id,long memberId,int kind,String context,String remart,int status,Date createDate,Date updateDate,int abopt,String data1,long data2,int data3,Double data4){
		super();
		this.id = id;
		this.memberId = memberId;
		this.kind = kind;
		this.context = context;
		this.remart = remart;
		this.status = status;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.abopt = abopt;
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

	public void setMemberId(Long memberId){
		this.memberId = memberId;
	}

	public Long getMemberId(){
		return this.memberId;
	}

	public void setKind(Integer kind){
		this.kind = kind;
	}

	public Integer getKind(){
		return this.kind;
	}

	public void setContext(String context){
		this.context = context;
	}

	public String getContext(){
		return this.context;
	}

	public void setRemart(String remart){
		this.remart = remart;
	}

	public String getRemart(){
		return this.remart;
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

	public void setAbopt(Integer abopt){
		this.abopt = abopt;
	}

	public Integer getAbopt(){
		return this.abopt;
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

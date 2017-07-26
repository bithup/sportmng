package com.xgh.mng.entity;
import java.io.Serializable;
import java.util.Date;


/**
 *
 * 种类表(热门分类)
 *
 **/
@SuppressWarnings("serial")
public class Kinds implements Serializable {

	/****/
	private long id;

	/**生产规则 101000+id**/
	private long nid;

	/**父Id**/
	private long parentId;

	/**行业id**/
	private long instId;

	/**行业编号**/
	private String instCode;

	/**操作人员的Id**/
	private long userId;

	/**种类编码**/
	private String code;

	/**父编码**/
	private String parentCode;

	/**前置code，从后向前递推处理**/
	private String preCode;

	/**分类名称**/
	private String name;

	/**拼音码**/
	private String spellName;

	/**拼音首字母**/
	private String spellFirstName;

	/**等级**/
	private int level;

	/**运动项图片路径**/
	private String sportUrl;

	/**运动项图片绝对路径**/
	private String sportRealUrl;

	/**创建时间**/
	private Date createDate;

	/**更新时间**/
	private Date updateDate;

	/**排序**/
	private long ord;

	/**0废弃，1使用**/
	private int status;

	/**备注**/
	private String remark;

	/**区域编码**/
	private String zoneCode;

	/**备用字段1**/
	private long data1;

	/**备用字段2**/
	private String data2;

	/**备用字段3**/
	private String data3;

	/**备用字段4**/
	private int data4;


	public Kinds() { super(); }

	public Kinds(long id) {
		super();
		this.id=id;
	}

	public Kinds(long id,long nid,long parentId,long instId,String instCode,long userId,String code,String parentCode,String preCode,String name,String spellName,String spellFirstName,int level,String sportUrl,String sportRealUrl,Date createDate,Date updateDate,long ord,int status,String remark,String zoneCode,long data1,String data2,String data3,int data4){
		super();
		this.id = id;
		this.nid = nid;
		this.parentId = parentId;
		this.instId = instId;
		this.instCode = instCode;
		this.userId = userId;
		this.code = code;
		this.parentCode = parentCode;
		this.preCode = preCode;
		this.name = name;
		this.spellName = spellName;
		this.spellFirstName = spellFirstName;
		this.level = level;
		this.sportUrl = sportUrl;
		this.sportRealUrl = sportRealUrl;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.ord = ord;
		this.status = status;
		this.remark = remark;
		this.zoneCode = zoneCode;
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

	public void setNid(Long nid){
		this.nid = nid;
	}

	public Long getNid(){
		return this.nid;
	}

	public void setParentId(Long parentId){
		this.parentId = parentId;
	}

	public Long getParentId(){
		return this.parentId;
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

	public void setUserId(Long userId){
		this.userId = userId;
	}

	public Long getUserId(){
		return this.userId;
	}

	public void setCode(String code){
		this.code = code;
	}

	public String getCode(){
		return this.code;
	}

	public void setParentCode(String parentCode){
		this.parentCode = parentCode;
	}

	public String getParentCode(){
		return this.parentCode;
	}

	public void setPreCode(String preCode){
		this.preCode = preCode;
	}

	public String getPreCode(){
		return this.preCode;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}

	public void setSpellName(String spellName){
		this.spellName = spellName;
	}

	public String getSpellName(){
		return this.spellName;
	}

	public void setSpellFirstName(String spellFirstName){
		this.spellFirstName = spellFirstName;
	}

	public String getSpellFirstName(){
		return this.spellFirstName;
	}

	public void setLevel(Integer level){
		this.level = level;
	}

	public Integer getLevel(){
		return this.level;
	}

	public void setSportUrl(String sportUrl){
		this.sportUrl = sportUrl;
	}

	public String getSportUrl(){
		return this.sportUrl;
	}

	public void setSportRealUrl(String sportRealUrl){
		this.sportRealUrl = sportRealUrl;
	}

	public String getSportRealUrl(){
		return this.sportRealUrl;
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

	public void setOrd(Long ord){
		this.ord = ord;
	}

	public Long getOrd(){
		return this.ord;
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

	public void setZoneCode(String zoneCode){
		this.zoneCode = zoneCode;
	}

	public String getZoneCode(){
		return this.zoneCode;
	}

	public void setData1(Long data1){
		this.data1 = data1;
	}

	public Long getData1(){
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

	public void setData4(Integer data4){
		this.data4 = data4;
	}

	public Integer getData4(){
		return this.data4;
	}

}

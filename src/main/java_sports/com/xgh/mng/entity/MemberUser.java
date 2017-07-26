package com.xgh.mng.entity;
import java.io.Serializable;
import java.util.Date;


/**
 * 
 * 
 * 
 **/
@SuppressWarnings("serial")
public class MemberUser implements Serializable {

	/**主键id**/
	private long id;

	/**会员标示uuid**/
	private String uuid;

	/**所属代理**/
	private long unitId;

	/**账号（手机号）**/
	private String account;

	/**密码（md5加密）**/
	private String password;

	/**是否验证手机号，默认没有验证（0：未验证  1：已验证）**/
	private int isVerify;

	/**注册ip**/
	private String registerIp;

	/**登录类型（1：安卓  2：IOS  3：微信）**/
	private String loginType;

	/**本次登录ip**/
	private String loginIp;

	/**注册设备（1：安卓  2：IOS  3：PC）**/
	private String regDevice;

	/**头像路径**/
	private String headPath;

	/**头像绝对路径**/
	private String headRealPath;

	/**真实姓名**/
	private String realName;

	/**身份证**/
	private String idCard;

	/**昵称**/
	private String nickName;

	/**性别**/
	private int sex;

	/**个性签名**/
	private String signature;

	/**生日**/
	private Date birthday;

	/**身高**/
	private Double heigth;

	/**体重**/
	private Double weigth;

	/**运动爱好**/
	private String hobby;

	/**创建时间**/
	private Date createDate;

	/**修改时间**/
	private Date updateDate;

	/**区域编码**/
	private String zoneCode;

	/**区域id**/
	private long zoneId;

	/**微信openId**/
	private String openId;

	/**登录时间**/
	private Date loginDate;

	/**备注**/
	private String remark;

	/**会员来源（1：前台注册  2：后台注册）**/
	private int userResource;

	/**是否参与代理（0：否  1：是）**/
	private int isAgent;

	/**状态（-1:已删除 0：正常 1:未删除）**/
	private int status;

	/****/
	private String payPassword;

	/****/
	private Double purseBalance;

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


	public MemberUser() { super(); }

	public MemberUser(long id) {
	 super();
	 this.id=id;
	}

	public MemberUser(long id,String uuid,long unitId,String account,String password,int isVerify,String registerIp,String loginType,String loginIp,String regDevice,String headPath,String headRealPath,String realName,String idCard,String nickName,int sex,String signature,Date birthday,Double heigth,Double weigth,String hobby,Date createDate,Date updateDate,String zoneCode,long zoneId,String openId,Date loginDate,String remark,int userResource,int isAgent,int status,String payPassword,Double purseBalance,String data1,String data2,String data3,long data4,long data5,long data6,int data7,int data8,int data9,Double data10,Double data11){
		super();
		this.id = id;
		this.uuid = uuid;
		this.unitId = unitId;
		this.account = account;
		this.password = password;
		this.isVerify = isVerify;
		this.registerIp = registerIp;
		this.loginType = loginType;
		this.loginIp = loginIp;
		this.regDevice = regDevice;
		this.headPath = headPath;
		this.headRealPath = headRealPath;
		this.realName = realName;
		this.idCard = idCard;
		this.nickName = nickName;
		this.sex = sex;
		this.signature = signature;
		this.birthday = birthday;
		this.heigth = heigth;
		this.weigth = weigth;
		this.hobby = hobby;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.zoneCode = zoneCode;
		this.zoneId = zoneId;
		this.openId = openId;
		this.loginDate = loginDate;
		this.remark = remark;
		this.userResource = userResource;
		this.isAgent = isAgent;
		this.status = status;
		this.payPassword = payPassword;
		this.purseBalance = purseBalance;
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

	public void setUuid(String uuid){
		this.uuid = uuid;
	}

	public String getUuid(){
		return this.uuid;
	}

	public void setUnitId(Long unitId){
		this.unitId = unitId;
	}

	public Long getUnitId(){
		return this.unitId;
	}

	public void setAccount(String account){
		this.account = account;
	}

	public String getAccount(){
		return this.account;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return this.password;
	}

	public void setIsVerify(Integer isVerify){
		this.isVerify = isVerify;
	}

	public Integer getIsVerify(){
		return this.isVerify;
	}

	public void setRegisterIp(String registerIp){
		this.registerIp = registerIp;
	}

	public String getRegisterIp(){
		return this.registerIp;
	}

	public void setLoginType(String loginType){
		this.loginType = loginType;
	}

	public String getLoginType(){
		return this.loginType;
	}

	public void setLoginIp(String loginIp){
		this.loginIp = loginIp;
	}

	public String getLoginIp(){
		return this.loginIp;
	}

	public void setRegDevice(String regDevice){
		this.regDevice = regDevice;
	}

	public String getRegDevice(){
		return this.regDevice;
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

	public void setRealName(String realName){
		this.realName = realName;
	}

	public String getRealName(){
		return this.realName;
	}

	public void setIdCard(String idCard){
		this.idCard = idCard;
	}

	public String getIdCard(){
		return this.idCard;
	}

	public void setNickName(String nickName){
		this.nickName = nickName;
	}

	public String getNickName(){
		return this.nickName;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public void setSignature(String signature){
		this.signature = signature;
	}

	public String getSignature(){
		return this.signature;
	}

	public void setBirthday(Date birthday){
		this.birthday = birthday;
	}

	public Date getBirthday(){
		return this.birthday;
	}

	public void setHeigth(Double heigth){
		this.heigth = heigth;
	}

	public Double getHeigth(){
		return this.heigth;
	}

	public void setWeigth(Double weigth){
		this.weigth = weigth;
	}

	public Double getWeigth(){
		return this.weigth;
	}

	public void setHobby(String hobby){
		this.hobby = hobby;
	}

	public String getHobby(){
		return this.hobby;
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

	public void setZoneCode(String zoneCode){
		this.zoneCode = zoneCode;
	}

	public String getZoneCode(){
		return this.zoneCode;
	}

	public void setZoneId(Long zoneId){
		this.zoneId = zoneId;
	}

	public Long getZoneId(){
		return this.zoneId;
	}

	public void setOpenId(String openId){
		this.openId = openId;
	}

	public String getOpenId(){
		return this.openId;
	}

	public void setLoginDate(Date loginDate){
		this.loginDate = loginDate;
	}

	public Date getLoginDate(){
		return this.loginDate;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getRemark(){
		return this.remark;
	}

	public void setUserResource(Integer userResource){
		this.userResource = userResource;
	}

	public Integer getUserResource(){
		return this.userResource;
	}

	public void setIsAgent(Integer isAgent){
		this.isAgent = isAgent;
	}

	public Integer getIsAgent(){
		return this.isAgent;
	}

	public void setStatus(Integer status){
		this.status = status;
	}

	public Integer getStatus(){
		return this.status;
	}

	public void setPayPassword(String payPassword){
		this.payPassword = payPassword;
	}

	public String getPayPassword(){
		return this.payPassword;
	}

	public void setPurseBalance(Double purseBalance){
		this.purseBalance = purseBalance;
	}

	public Double getPurseBalance(){
		return this.purseBalance;
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

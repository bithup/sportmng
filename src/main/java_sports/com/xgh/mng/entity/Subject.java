package com.xgh.mng.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/7 0007.
 */
public class Subject implements Serializable{

    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = 1L;

    public static final String key = "keySubject";
    //主键id
    private long id;
    //行业Id
    private long instId;
    //行业code
    private String instCode;
    //公司Id
    private long unitId;
    //公司编号
    private String unitCode;
    //创建人id
    private long userId;
    //主题类型
    private int type;
    //根路径
    private String path;
    //相对路径
    private String relativePath;
    //ios图片存储位置
    private String iosFilePath;
    //android图片存储路径
    private String androidFilePath;
    //手机网站图片存储路径
    private String mobileFilePath;
    //pc图片存储位置
    private String pcFilePath;
    //主题名称
    private String subjectName;
    //1 商品列表，2 商品详情 3 url页面，4 宣传页面
    private int subjectType;
    //类型为3的时候存储url地址
    private String subjectUrl;
    //主题html内容，subject_type为4时，存储html富文本内容
    private String subjectContent;
    //创建时间
    private Date createDate;
    //创建时间
    private Date updateDate;
    //活动排序
    private int ord;
    //主题备注
    private String remark;
    //-1：删除、0：不启用、1：启用
    private int status;

    //主题提示
    private String subjectHint;

    //轮播图打开地址
    private String sujectUrl;

    public Subject(){
        super();
    }

    public Subject(long id){
        super();
        this.id = id;
    }

    public Subject(long id,long instId,String instCode,long unitId,String unitCode,long userId,int type,String path,String relativePath,String iosFilePath,String androidFilePath,String mobileFilePath,String pcFilePath,String subjectName,int subjectType,String subjectUrl,String subjectContent,Date createDate,Date updateDate,int ord,String remark,int status,String subjectHint,String sujectUrl){
        this.id = id;
        this.instId = instId;
        this.instCode = instCode;
        this.unitId = unitId;
        this.unitCode = unitCode;
        this.userId = userId;
        this.type = type;
        this.path = path;
        this.relativePath = relativePath;
        this.iosFilePath = iosFilePath;
        this.androidFilePath = androidFilePath;
        this.mobileFilePath = mobileFilePath;
        this.pcFilePath = pcFilePath;
        this.subjectName = subjectName;
        this.subjectType = subjectType;
        this.subjectUrl = subjectUrl;
        this.subjectContent = subjectContent;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.ord = ord;
        this.remark = remark;
        this.status = status;
        this.subjectHint= subjectHint;
        this.sujectUrl= sujectUrl;
    }

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public long getInstId(){
        return instId;
    }

    public void setInstId(long instId){
        this.instId = instId;
    }

    public String getInstCode(){
        return instCode;
    }

    public void setInstCode(String instCode){
        this.instCode = instCode;
    }

    public long getUnitId(){
        return unitId;
    }

    public void setUnitId(long unitId){
        this.unitId = unitId;
    }

    public String getUnitCode(){
        return unitCode;
    }

    public void setUnitCode(String unitCode){
        this.unitCode = unitCode;
    }

    public long getUserId(){
        return userId;
    }

    public void setUserId(long userId){
        this.userId = userId;
    }

    public int getType(){
        return type;
    }

    public void setType(int type){
        this.type = type;
    }

    public String getPath(){
        return path;
    }

    public void setPath(String path){
        this.path = path;
    }

    public String getRelativePath(){
        return relativePath;
    }

    public void setRelativePath(String relativePath){
        this.relativePath = relativePath;
    }

    public String getIosFilePath(){
        return iosFilePath;
    }

    public void setIosFilePath(String iosFilePath){
        this.iosFilePath = iosFilePath;
    }

    public String getAndroidFilePath(){
        return androidFilePath;
    }

    public void setAndroidFilePath(String androidFilePath){
        this.androidFilePath = androidFilePath;
    }


    public String getMobileFilePath(){
        return mobileFilePath;
    }

    public void setMobileFilePath(String mobileFilePath){
        this.mobileFilePath = mobileFilePath;
    }

    public String getPcFilePath(){
        return pcFilePath;
    }

    public void setPcFilePath(String pcFilePath){
        this.pcFilePath = pcFilePath;
    }

    public String getSubjectName(){
        return subjectName;
    }

    public void setSubjectName(String subjectName){
        this.subjectName = subjectName;
    }

    public int getSubjectType(){
        return subjectType;
    }

    public void setSubjectType(int subjectType){
        this.subjectType = subjectType;
    }


    public String getSubjectUrl(){
        return subjectUrl;
    }

    public void setSubjectUrl(String subjectUrl){
        this.subjectUrl = subjectUrl;
    }

    public String getSubjectContent(){
        return subjectContent;
    }

    public void setSubjectContent(String subjectContent){
        this.subjectContent = subjectContent;
    }

    public Date getCreateDate(){
        return createDate;
    }

    public void setCreateDate(Date createDate){
        this.createDate = createDate;
    }

    public Date getUpdateDate(){
        return updateDate;
    }

    public void setUpdateDate(Date updateDate){
        this.updateDate = updateDate;
    }


    public int getOrd(){
        return ord;
    }

    public void setOrd(int ord){
        this.ord = ord;
    }

    public String getRemark(){
        return remark;
    }

    public void setRemark(String remark){
        this.remark = remark;
    }

    public int getStatus(){
        return status;
    }

    public void setStatus(int status){
        this.status = status;
    }

    public String getSubjectHint() {
        return subjectHint;
    }

    public void setSubjectHint(String subjectHint) {
        this.subjectHint= subjectHint;
    }

    public String getSujectUrl() {
        return sujectUrl;
    }

    public void setSujectUrl(String sujectUrl) {
        this.sujectUrl = sujectUrl;
    }

    public String toString(){
        return "id:"+id+"\t"+"instId:"+instId+"\t"+"instCode:"+instCode+"\t"+"unitId:"+unitId+"\t"+"unitCode:"+unitCode+"\t"+"userId:"+userId+"\t"+"type:"+type+"\t"+"path:"+path+"\t"+"relativePath:"+relativePath+"\t"+"iosFilePath:"+iosFilePath+"\t"+"androidFilePath:"+androidFilePath+"\t"+"mobileFilePath:"+mobileFilePath+"\t"+"pcFilePath:"+pcFilePath+"\t"+"subjectName:"+subjectName+"\t"+"subjectType:"+subjectType+"\t"+"subjectUrl:"+subjectUrl+"\t"+"subjectContent:"+subjectContent+"\t"+"createDate:"+createDate+"\t"+"updateDate:"+updateDate+"\t"+"ord:"+ord+"\t"+"remark:"+remark+"\t"+"status:"+status;
    }
}

package com.xgh.mng.entity;

import java.io.Serializable;
import java.io.StringReader;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/7 0007.
 */
public class AosSubject implements Serializable{
    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = 1L;

    public static final String key = "keyAosSubject";

    //主键id
    private long id;

    //行业id
    private long instId;

    //行业编号
    private String instCode;

    //公司id
    private long unitId;

    //公司编号
    private String unitCode;

    //创建人id
    private long userId;

    //轮播图id
    private long dataId;

    //轮播图类型（1：场馆 2：教练 3：活动）
    private int dataType;

    //场馆/教练/活动id
    private long subjectId;

    //场馆/教练/活动名称
    private String subjectName;

    //logo路径
    private String logoPath;

    //logo绝对路径
    private String logoRealPath;

    //是否加入热门(0:否 1：是)
    private int isSubject;

    //描述
    private String remark;

    //开始时间
    private Date startDate;

    //结束时间
    private Date endDate;

    //创建时间
    private Date createDate;

    //修改时间
    private Date updateDate;

    //排序
    private int ord;

    //状态（0：已删除 1：未删除）
    private int status;

    //地区编码
    private String zoneCode;

    public AosSubject(){
        super();
    }
    public AosSubject(long id){
        super();
        this.id=id;
    }
    public AosSubject(long id, long instId, String instCode, long unitId, String unitCode, long userId, long dataId, int dataType, long subjectId, String subjectName, String logoPath, String logoRealPath, int isSubject, String remark, Date startDate, Date endDate, Date createDate, Date updateDate, int ord, int status, String zoneCode){
        this.id=id;
        this.instId=instId;
        this.instCode=instCode;
        this.unitId=unitId;
        this.unitCode=unitCode;
        this.userId=userId;
        this.dataId=dataId;
        this.dataType=dataType;
        this.subjectId=subjectId;
        this.subjectName=subjectName;
        this.logoPath=logoPath;
        this.logoRealPath=logoRealPath;
        this.isSubject=isSubject;
        this.remark=remark;
        this.startDate=startDate;
        this.endDate=endDate;
        this.createDate=createDate;
        this.updateDate=updateDate;
        this.ord=ord;
        this.status=status;
        this.zoneCode=zoneCode;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getInstId() {
        return instId;
    }

    public void setInstId(long instId) {
        this.instId = instId;
    }

    public String getInstCode() {
        return instCode;
    }

    public void setInstCode(String instCode) {
        this.instCode = instCode;
    }

    public long getUnitId() {
        return unitId;
    }

    public void setUnitId(long unitId) {
        this.unitId = unitId;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getDataId() {
        return dataId;
    }

    public void setDataId(long dataId) {
        this.dataId = dataId;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(long subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public String getLogoRealPath() {
        return logoRealPath;
    }

    public void setLogoRealPath(String logoRealPath) {
        this.logoRealPath = logoRealPath;
    }

    public int getIsSubject() {
        return isSubject;
    }

    public void setIsSubject(int isSubject) {
        this.isSubject = isSubject;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getZoneCode() {
        return zoneCode;
    }

    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode;
    }
}

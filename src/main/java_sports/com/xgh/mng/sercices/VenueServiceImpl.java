package com.xgh.mng.sercices;

import com.xgh.mng.basic.BaseService;
import com.xgh.mng.dao.IMemberUserDao;
import com.xgh.mng.dao.IVenueDao;
import com.xgh.mng.dao.IZoneDao;
import com.xgh.mng.entity.*;
import com.xgh.mng.services.IFileDataService;
import com.xgh.mng.services.IZoneService;
import com.xgh.security.Base64Util;
import com.xgh.security.MD5Util;
import com.xgh.util.ConstantUtil;
import com.xgh.util.JSONUtil;
import com.xgh.util.JSONValidator;
import com.xgh.util.ShortMessageUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2016/10/19.
 */

@Service("venueService")
public class VenueServiceImpl extends BaseService implements IVenueService {

    @Autowired
    protected IShortMessageService shortMessageService;

    @Autowired
    protected IVenueDao venueDao;

    @Autowired
    protected IFileDataServiceNew fileDataService;

    @Autowired
    protected IZoneService zoneService;

    @Autowired
    protected IZoneDao zoneDao;

    @Autowired
    protected IMemberUserDao memberUserDao;


    public Venue get(long id) {
        Venue venue = venueDao.get(id);
        String licenseUrl = ConstantUtil.SERVER_URL+venue.getLicenseUrl();
        String pictureUrl = ConstantUtil.SERVER_URL+venue.getPictureUrl();
        String idCardFront = ConstantUtil.SERVER_URL+venue.getIdCardFront();
        String idCardBack = ConstantUtil.SERVER_URL+venue.getIdCardBack();
        venue.setLicenseUrl(licenseUrl);
        venue.setPictureUrl(pictureUrl);
        venue.setIdCardFront(idCardFront);
        venue.setIdCardBack(idCardBack);
        return venue;
    }

    public Map<String, Object> getListPage(Map<String, Object> map) {
        List<Map<String,Object>> list = venueDao.getListPage(map);
        for(Map<String,Object> vmap:list){
            if(null!=String.valueOf(vmap.get("isCheck"))&&!"".equals(String.valueOf(vmap.get("isCheck")))){
                if(Integer.parseInt(String.valueOf(vmap.get("isCheck")))==0){
                    vmap.put("isCheck","未审核");
                }else if(Integer.parseInt(String.valueOf(vmap.get("isCheck")))==1){
                    vmap.put("isCheck","审核通过");
                }
                else if(Integer.parseInt(String.valueOf(vmap.get("isCheck")))==2){
                    vmap.put("isCheck","审核未通过");
                }
            }
            if(null!=String.valueOf(vmap.get("isRecommend"))&&!"".equals(String.valueOf(vmap.get("isRecommend")))){
                if(Integer.parseInt(String.valueOf(vmap.get("isRecommend")))==0){
                    vmap.put("isRecommend","否");
                }else if(Integer.parseInt(String.valueOf(vmap.get("isRecommend")))==1){
                    vmap.put("isRecommend","是");
                }
            }
        }
        HashMap<String,Object> gridMap = new HashMap<String, Object>();
        if(list==null){
            list = new ArrayList<Map<String, Object>>();
        }
        gridMap.put("Rows",list);
        gridMap.put("Total",venueDao.getRows(map));
        return gridMap;
    }

    public int add(Venue venue) {
        return venueDao.add(venue);
    }

    public int update(Venue venue) {
        return venueDao.update(venue);
    }

    public int delete(long id) {
        return venueDao.delete(id);
    }

    public int save(HttpServletRequest request,Venue venue){
        long id = venue.getId();
        Zone zone = zoneDao.get(venue.getZoneId());
        venue.setZoneCode(zone.getCode());
        venue.setZoneName(getZoneName(venue.getZoneCode()));
        venue.setInstId(getCurrentInstId(request));
        SysUnits sysUnits = getCurrentUnits(request);
        venue.setInstCode(sysUnits.getInstCode());
        venue.setUnitId(getCurrentUnitId(request));
        venue.setUnitCode(sysUnits.getUnitCode());
        if(id>0){
            Venue venue1 = venueDao.get(id);
            venue.setCreateDate(venue1.getCreateDate());
            venue.setIsCheck(venue1.getIsCheck());
            venue.setIsRecommend(venue1.getIsRecommend());
            venue.setUpdateDate(new Date());
            if (addFileData(request, venue))
                return venueDao.update(venue);
            else
                return 0;

        }else{
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("account",venue.getMobile());
            int count = memberUserDao.getRepetitionMembers(map);
            if(count>0){
                return 2;
            }else {
                venue.setCreateDate(new Date());
                venue.setUpdateDate(new Date());
                venue.setIsCheck(0);
                venue.setStatus(0);
                int flag = venueDao.add(venue);
                if (addFileData(request, venue)) {
                    MemberUser memberUser = new MemberUser();
                    memberUser.setCreateDate(new Date());
                    memberUser.setUpdateDate(new Date());
                    memberUser.setUnitId(getCurrentUnitId(request));
                    memberUser.setUuid(UUID.randomUUID().toString());
                    memberUser.setZoneId(venue.getZoneId());
                    memberUser.setZoneCode(venue.getZoneCode());
                    memberUser.setSex(venue.getSex());
                    memberUser.setRealName(venue.getName());
                    memberUser.setNickName(venue.getName());
                    memberUser.setAccount(venue.getMobile());
                    memberUser.setHeadPath(venue.getPictureUrl());
                    memberUser.setHeadRealPath(venue.getPicRealPath());
                    memberUser.setUserResource(2);
                    memberUser.setData7(3);
                    memberUser.setData4(venue.getId());
                    //暂时作为默认密码处理
                    memberUser.setPassword(MD5Util.getMD5("123456"));
                    memberUserDao.insert(memberUser);
                    ShortMessageUtil shortMessageUtil = new ShortMessageUtil();
                    ShortMessage shortMessage = shortMessageUtil.sendMessage(memberUser.getAccount());
                    shortMessageService.insert(shortMessage);
                    int i = shortMessage.getSatatus();
                    while (i==0){
                        i = shortMessageUtil.sendMessage(memberUser.getAccount()).getSatatus();
                    }
                    return flag;
                } else {
                    return 0;
                }
            }
        }
    }

    /**
     * 添加场馆信息图片
     */
    private Venue setSubjectImg(HttpServletRequest request, Venue venue) {
        String licensePicData_ = request.getParameter("licensePicData");
        String venuePicData_ = request.getParameter("venuePicData");
        String frontPicData_ = request.getParameter("frontPicData");
        String backPicData_ = request.getParameter("backPicData");
        //存储位置
        String saveToPath = ConstantUtil.SERVER_URL;
        if (JSONValidator.validate(licensePicData_)) {
            Map<String, Object> licensePicData = JSONUtil.getMap(licensePicData_);
            if (licensePicData.size() > 1) {
                venue.setLicenseUrl(saveToPath+copyFile(licensePicData));
            }
        }
        if (JSONValidator.validate(venuePicData_)) {
            Map<String, Object> venuePicData = JSONUtil.getMap(venuePicData_);
            if (venuePicData.size() > 1) {
                venue.setPictureUrl(copyFile(venuePicData));
                venue.setPicRealPath(saveToPath+copyFile(venuePicData));
            }
        }
        if (JSONValidator.validate(frontPicData_)) {
            Map<String, Object> frontPicData = JSONUtil.getMap(frontPicData_);
            if (frontPicData.size() > 1) {
                venue.setIdCardFront(saveToPath+copyFile(frontPicData));
            }
        }
        if (JSONValidator.validate(backPicData_)) {
            Map<String, Object> backPicData = JSONUtil.getMap(backPicData_);
            if (backPicData.size() > 1) {
                venue.setIdCardBack(saveToPath+copyFile(backPicData));
            }
        }
        return venue;
    }

    /**
     * 添加场馆图片
     *
     * @param request
     * @param
     */
    private boolean addFileData(HttpServletRequest request, Venue venue) {
        String[] venuePicsData_array = request.getParameterValues("venuePicsData");
        String[] licensePicData_array = request.getParameterValues("licensePicData");
        String[] venuePicData_array = request.getParameterValues("venuePicData");

        // 店铺图片信息
        fileDataService.saveFiles(request, venuePicsData_array, venue.getId(), ConstantUtil.FileUploadCode.Venue,
                0,0);
        List<FileData> licensePicList =  fileDataService.saveFiles(request, licensePicData_array, venue.getId(), ConstantUtil.FileUploadCode.Venue,
                0,1);
        if (licensePicList != null && licensePicList.size() > 0) {
            FileData fileData = licensePicList.get(licensePicList.size() - 1);
            String serverPath = ConstantUtil.SERVER_URL;
            venue.setLicenseUrl(serverPath+"/" + fileData.getRelativePath() + "/" + fileData.getFileName());
        }
        List<FileData> venuePicList = fileDataService.saveFiles(request, venuePicData_array, venue.getId(), ConstantUtil.FileUploadCode.Venue,
                0,2);
        if (venuePicList != null && venuePicList.size() > 0) {
            FileData fileData = venuePicList.get(venuePicList.size() - 1);
            String serverPath = ConstantUtil.SERVER_URL;
            venue.setPictureUrl("/" + fileData.getRelativePath() + "/" + fileData.getFileName());
            venue.setPicRealPath(serverPath+"/" + fileData.getRelativePath() + "/" + fileData.getFileName());
        }
    /*    venueDao.update(venue);*/

        venueDao.update(venue);

        return true;
    }

    private String copyFile(Map<String, Object> map) {
        String srcPath = null;
        try {
            srcPath = Base64Util.decodeToString(map.get("savePath") + "");
            String fileName = map.get("fileName") + "";
            //存储位置
            String saveToPath = ConstantUtil.SERVER_URL;
            //日期作为相对路径
            SimpleDateFormat formatdate = new SimpleDateFormat("yyyy/MM/dd/");
            String _relative_path = formatdate.format(new Date());

            String relative_path = "/" + ConstantUtil.FileUploadCode.Venue.value() + "/" + _relative_path + fileName;

            String destPath = saveToPath + relative_path;

            FileUtils.copyFile(new File(srcPath), new File(destPath));

            return relative_path;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getZoneName( String zoneCode){
        Zone zone =  zoneService.getZoneByCode(zoneCode);
        String zoneName="";
        if(Integer.parseInt(zone.getLevel())==5){
            Zone zone4 = zoneService.getZoneByCode(zone.getPcode());
            Zone zone3 = zoneService.getZoneByCode(zone4.getPcode());
            Zone zone2 = zoneService.getZoneByCode(zone3.getPcode());
            Zone zone1 = zoneService.getZoneByCode(zone2.getPcode());
            zoneName = zone3.getName()+zone4.getName()+zone.getName();
        }else if(Integer.parseInt(zone.getLevel())==4){
            Zone zone3 = zoneService.getZoneByCode(zone.getPcode());
            Zone zone2 = zoneService.getZoneByCode(zone3.getPcode());
            Zone zone1 = zoneService.getZoneByCode(zone2.getPcode());
            zoneName = zone3.getName()+zone.getName();
        }else if(Integer.parseInt(zone.getLevel())==3){
            Zone zone2 = zoneService.getZoneByCode(zone.getPcode());
            Zone zone1 = zoneService.getZoneByCode(zone2.getPcode());
            zoneName = zone2.getName()+zone.getName();
        }
        else if(Integer.parseInt(zone.getLevel())==2){
            Zone zone1 = zoneService.getZoneByCode(zone.getPcode());
            zoneName = zone1.getName()+zone.getName();
        }else if(Integer.parseInt(zone.getLevel())==1){
            zoneName = zone.getName();
        }
        return zoneName;
    }

    /**
     * 获取场馆下拉框信息
     * @param instId
     * @return
     */
    public List<Map<String, Object>> getVenue(long instId) {
        HashMap paramMap = new HashMap();
        paramMap.put("instId", Long.valueOf(instId));
        Object list = this.venueDao.getVenue(paramMap);
        if(list == null) {
            list = new ArrayList();
        }
        return (List)list;
    }

    /**
     * 场馆审核
     * @param venue
     * @return
     */
    public int checkVenue(Venue venue){
        return venueDao.checkVenue(venue);
    }

}

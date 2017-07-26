package com.xgh.mng.sercices;

import com.xgh.mng.basic.BaseService;
import com.xgh.mng.dao.IMemberUserDao;
import com.xgh.mng.dao.IZoneDao;
import com.xgh.mng.entity.FileData;
import com.xgh.mng.entity.MemberUser;
import com.xgh.mng.entity.Venue;
import com.xgh.mng.entity.Zone;
import com.xgh.security.MD5Util;
import com.xgh.util.ConstantUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

/**
 * Created by Administrator on 2016/11/7.
 */
@Service("memberUserService")
public class MemberUserServiceImpl extends BaseService implements IMemberUserService {

    @Autowired
    protected IMemberUserDao memberUserDao;

    @Autowired
    protected IFileDataServiceNew fileDataService;

    @Autowired
    protected IZoneDao zoneDao;

    public int insert(MemberUser memberUser) {
        return memberUserDao.insert(memberUser);
    }

    public int update(MemberUser memberUser) {
        return memberUserDao.update(memberUser);
    }

    public int delete(long id) {
        return memberUserDao.delete(id);
    }

    public MemberUser get(long id) {
        return memberUserDao.get(id);
    }

    public Map<String, Object> getGridList(HttpServletRequest request) {

        Map<String,Object> gridMap = new HashMap<String, Object>();
        Map<String,Object> map = new HashMap<String, Object>();

        String account = request.getParameter("account");
        String realName = request.getParameter("realName");
/*      try{
            realName = new String(realName.getBytes("ISO8859-1"),"UTF-8");
        }catch (IOException e){
            e.printStackTrace();
        }*/
        String idCard = request.getParameter("idCard");
        String page = request.getParameter("page");
        String pagesize = request.getParameter("pagesize");
        map.put("account",account);
        map.put("realName",realName);
        map.put("idCard",idCard);
        map.put("page",Integer.parseInt(page));
        map.put("pagesize",Integer.parseInt(pagesize));
        List<Map<String,Object>> list = memberUserDao.getListPage(map);
        for(Map<String,Object> memMap:list){
            if(memMap.get("sex")!=null&&!"".equals(String.valueOf(memMap.get("sex")))){
                if(Integer.parseInt(String.valueOf(memMap.get("sex")))==1){
                    memMap.put("sex","男");
                }else if(Integer.parseInt(String.valueOf(memMap.get("sex")))==2){
                    memMap.put("sex","女");
                }
            }
            if(memMap.get("zoneCode")!=null&&!"".equals(String.valueOf(memMap.get("zoneCode")))){
                String[] preFix = String.valueOf(memMap.get("preFix")).split("_");
                String zoneName = "";
                for(int i=0;i<preFix.length;i++){
                    if(i>1){
                        zoneName += zoneDao.get(Long.parseLong(preFix[i])).getName();
                        memMap.put("zoneName",zoneName);
                    }
                }
            }
            if(memMap.get("data7")!=null&&!"".equals(memMap.get("data7")+"")){
                int data7 = Integer.parseInt(String.valueOf(memMap.get("data7")));
                if(data7==1){
                    memMap.put("data7","用户");
                }else if(data7==2){
                    memMap.put("data7","教练");
                }else if(data7==3){
                    memMap.put("data7","场馆");
                }
            }
        }
        gridMap.put("Rows",list);
        gridMap.put("Total",memberUserDao.getRows(map));
        return gridMap;
    }

    public int save(HttpServletRequest request,MemberUser memberUser){
        long id = memberUser.getId();
        if(id>0){
            MemberUser member  = memberUserDao.get(memberUser.getId());
            member.setAccount(memberUser.getAccount());
            member.setSex(memberUser.getSex());
            member.setNickName(memberUser.getNickName());
            member.setRealName(memberUser.getRealName());
            member.setIdCard(memberUser.getIdCard());
            member.setZoneId(memberUser.getZoneId());
            Zone zone = zoneDao.get(memberUser.getZoneId());
            member.setZoneCode(zone.getCode());
            member.setUpdateDate(new Date());
            if(addFileData(request,member)){
                return memberUserDao.update(member);
            }else{
                return 0;
            }
        }else{

            Map<String,Object> map = new HashMap<String, Object>();
            map.put("account",memberUser.getAccount());
            int count = memberUserDao.getRepetitionMembers(map);
            int flag = 0;
            if(count>0){
                flag=2;
                return flag;
            }else{
                memberUser.setCreateDate(new Date());
                memberUser.setUpdateDate(new Date());
                memberUser.setUnitId(getCurrentUnitId(request));
                memberUser.setUuid(UUID.randomUUID().toString());
                if(request.getParameter("zoneId")!=null&&!"".equals(request.getParameter("zoneId"))&&Integer.parseInt(request.getParameter("zoneId"))>0){
                    long zoneId = Long.parseLong(request.getParameter("zoneId"));
                    Zone zone = zoneDao.get(zoneId);
                    memberUser.setZoneCode(zone.getCode());
                }
                memberUser.setUserResource(2);
                memberUser.setData7(1);
                //暂时作为默认密码处理
                memberUser.setPassword(MD5Util.getMD5("123456"));
                flag = memberUserDao.insert(memberUser);
                if(addFileData(request,memberUser)){
                    return flag;
                }else{
                    return 0;
                }
            }
        }
    }

    /**
     * 添加场馆图片
     *
     * @param request
     * @param
     */
    private boolean addFileData(HttpServletRequest request, MemberUser memberUser) {
        String[] logoData_array = request.getParameterValues("logoPicData");

        List<FileData> licensePicList =  fileDataService.saveFiles(request, logoData_array, memberUser.getId(), ConstantUtil.FileUploadCode.Member,
                0,0);
        if (licensePicList != null && licensePicList.size() > 0) {
            FileData fileData = licensePicList.get(licensePicList.size() - 1);
            String serverPath = ConstantUtil.SERVER_URL;
            memberUser.setHeadRealPath(serverPath + fileData.getRelativePath()  + fileData.getFileName());
            memberUser.setHeadPath( fileData.getRelativePath() + fileData.getFileName());
        }
        memberUserDao.update(memberUser);
        return true;
    }

    public int getRepetitionMembers(Map<String, Object> map) {
        return memberUserDao.getRepetitionMembers(map);
    }
}

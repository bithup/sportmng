package com.xgh.mng.sercices;

import com.xgh.mng.basic.BaseService;
import com.xgh.mng.dao.ICoachDao;
import com.xgh.mng.dao.IMemberUserDao;
import com.xgh.mng.dao.IZoneDao;
import com.xgh.mng.entity.*;

import com.xgh.mng.services.IFileDataService;
import com.xgh.security.MD5Util;
import com.xgh.util.ConstantUtil;
import com.xgh.util.ShortMessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

/**
 * Created by CQ on 2016/11/2.
 */
@Service("CoachService")
public class CoachServiceImpl extends BaseService implements ICoachService {

    @Autowired
    protected ICoachDao coachDao;


    @Autowired
    protected IFileDataServiceNew fileDataService;

    @Autowired
    protected IMemberUserDao memberUserDao;


    @Autowired
    protected IShortMessageService shortMessageService;


    @Autowired
    protected IZoneDao zoneDao;

    public Coach get(long id) {
        Coach coach = coachDao.get(id);
        String idCardFront = ConstantUtil.SERVER_URL+coach.getIdCardFront();
        String idCardBack = ConstantUtil.SERVER_URL+coach.getIdCardBack();
        String headPath = ConstantUtil.SERVER_URL+ coach.getHeadPath();
        coach.setIdCardFront(idCardFront);
        coach.setIdCardBack(idCardBack);
        coach.setHeadPath(headPath);
        return coach;
    }

    public List<Coach> getList(Coach coach) {
        return coachDao.getList(coach);
    }

    public List<Map<String, Object>> getListPage(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        return coachDao.getListPage(map);
    }

    public long getRows(Map<String, Object> map) {
        return 0;
    }

    public int add(Coach coach) {
        return 0;
    }

    public int update(Coach coach) {
        return coachDao.update(coach);
    }

    public int deleteById(long id) {
        return 0;
    }

    public int deleteByNid(long nid) {
        return 0;
    }

    public int save(HttpServletRequest request, Coach coach, String op) {

        Zone zone = zoneDao.get(coach.getData6());
        coach.setData1(zone.getCode());

        if ("modify".equals(op)) {
            Coach coach_ = coachDao.get(coach.getId());
            coach.setCreateDate(coach_.getCreateDate());
            coach.setIsCheck(coach_.getIsCheck());
            SysUnits sysUnits = getCurrentUnits(request);
            long instId = getCurrentInstId(request);
            coach.setUpdateDate(new Date());
            coach.setStatus(1);
            coach.setData4(sysUnits.getId());
            coach.setData5(instId);
            coachDao.update(coach);
            if (addFileData(request, coach))
                return 1;
            else
                return 0;
        } else {

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("account", coach.getTelPhone());
            int count = memberUserDao.getRepetitionMembers(map);
            if (count > 0) {
                return 2;
            } else {
                long unitId = getCurrentUnitId(request);
                long instId = getCurrentInstId(request);
                coach.setData4(unitId);
                Date date = new Date();
                coach.setCreateDate(date);
                coach.setData5(instId);
                coach.setUpdateDate(date);
                coach.setStatus(1);
                coachDao.add(coach);
                if (addFileData(request, coach)) {
                    MemberUser memberUser = new MemberUser();
                    memberUser.setCreateDate(new Date());
                    memberUser.setUpdateDate(new Date());
                    memberUser.setUnitId(getCurrentUnitId(request));
                    memberUser.setUuid(UUID.randomUUID().toString());
                    memberUser.setSex(coach.getSex());
                    memberUser.setAccount(coach.getTelPhone());
                    memberUser.setRealName(coach.getName());
                    memberUser.setBirthday(coach.getBirthday());
                    memberUser.setIsVerify(coach.getIsTrue());
                    memberUser.setHeadPath(coach.getHeadPath());
                    memberUser.setHeadRealPath(coach.getHeadRealPath());
                    memberUser.setUserResource(2);
                    memberUser.setData7(2);
                    memberUser.setData4(coach.getId());
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
                    return 1;
                }else{
                    return 0;
                }

            }
        }

    }

    /**
     * 分页下拉列表数据
     *
     * @param request
     * @return
     */
    public Map<String, Object> getGridList(HttpServletRequest request) {
        String page = request.getParameter("page");
        String pagesize = request.getParameter("pagesize");
        String isRecommend = request.getParameter("isRecommend");
        String isCheck = request.getParameter("isCheck");
        String name = request.getParameter("name");
/*        if(null!=name&&!"".equals(name)) {
            try {
                name = new String(name.getBytes("ISO8859-1"), "UTF-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("unitId", getCurrentUnitId(request));
        map.put("instId", getCurrentInstId(request));
        map.put("page", Integer.parseInt(page));
        map.put("pagesize", Integer.parseInt(pagesize));
        map.put("isRecommend",isRecommend);
        map.put("name",name);
        map.put("isCheck",isCheck);

        Map<String, Object> gridMap = new HashMap<String, Object>();
        List<Map<String, Object>> dataList = coachDao.getListPage(map);
        for(Map<String, Object> coachMap:dataList){
            if(String.valueOf(coachMap.get("sex"))!=null&&!"".equals(String.valueOf(coachMap.get("sex")))){
                if (Integer.parseInt(String.valueOf(coachMap.get("sex"))) == 2) {
                    coachMap.put("sex", "女");
                } else if (Integer.parseInt(String.valueOf(coachMap.get("sex"))) == 1) {
                    coachMap.put("sex", "男");
                }
            }
            if(String.valueOf(coachMap.get("isTrue"))!=null&&!"".equals(String.valueOf(coachMap.get("isTrue")))){
                if (Integer.parseInt(String.valueOf(coachMap.get("isTrue"))) == 0) {
                    coachMap.put("isTrue", "未认证");
                } else if (Integer.parseInt(String.valueOf(coachMap.get("isTrue"))) == 1) {
                    coachMap.put("isTrue", "已认证");
                }
            }
            if (String.valueOf(coachMap.get("isRecommend"))!=null){
                if (Integer.parseInt(String.valueOf(coachMap.get("isRecommend")))==0){
                    coachMap.put("isRecommend","否");
                }else if (Integer.parseInt(String.valueOf(coachMap.get("isRecommend")))==1){
                    coachMap.put("isRecommend","是");
                }
            }
            if (String.valueOf(coachMap.get("isCheck"))!=null) {
                if (Integer.parseInt(String.valueOf(coachMap.get("isCheck"))) == 0) {
                    coachMap.put("isCheck", "未审核");
                } else if (Integer.parseInt(String.valueOf(coachMap.get("isCheck"))) == 1) {
                    coachMap.put("isCheck", "通过");
                } else if (Integer.parseInt(String.valueOf(coachMap.get("isCheck"))) == 2) {
                    coachMap.put("isCheck", "未通过");
                }
            }
            if(String.valueOf(coachMap.get("height"))!=null){
                coachMap.put("height",coachMap.get("height")+"cm");
            }
            if(String.valueOf(coachMap.get("weight"))!=null){
                coachMap.put("weight",coachMap.get("weight")+"kg");
            }
        }
        if (dataList == null) {
            dataList = new ArrayList<Map<String, Object>>();
        }
        gridMap.put("Rows", dataList);
        gridMap.put("Total", coachDao.getRows(map));
        return gridMap;
    }

    /**
     * 添加门店图片
     *
     * @param request
     * @param
     */
    private boolean addFileData(HttpServletRequest request, Coach coach) {

        String[] logoData_array = request.getParameterValues("logoData");
        String[] picData_array = request.getParameterValues("picData");
        String[] logoIdFrontData_array = request.getParameterValues("logoIdFrontData");

         String[] logoIdBackData_array = request.getParameterValues("logoIdBackData");

        // 店铺Logo图片信息
        List<FileData> fileDataList = fileDataService.saveFiles(request, logoData_array, coach.getId(), ConstantUtil.FileUploadCode.Coach,
                0,2);
        if (fileDataList != null && fileDataList.size() > 0) {
            FileData fileData = fileDataList.get(fileDataList.size() - 1);
            coach.setHeadPath(fileData.getRelativePath()  + fileData.getFileName());
            coach.setHeadRealPath(ConstantUtil.SERVER_URL + fileData.getRelativePath()  + fileData.getFileName());
            coachDao.update(coach);
        }

        //身份证正面
        List<FileData> fileDataListIdFront = fileDataService.saveFiles(request, logoIdFrontData_array, coach.getId(), ConstantUtil.FileUploadCode.Coach,
                0,1);
        if (fileDataListIdFront != null && fileDataListIdFront.size() > 0) {
            FileData fileData = fileDataListIdFront.get(fileDataListIdFront.size() - 1);
            coach.setIdCardFront(fileData.getRelativePath()  + fileData.getFileName());
            coachDao.update(coach);
        }
        //身份证背面
        List<FileData> fileDataListIdBack = fileDataService.saveFiles(request, logoIdBackData_array, coach.getId(), ConstantUtil.FileUploadCode.Coach,
                0,3);
        if (fileDataListIdBack != null && fileDataListIdBack.size() > 0) {
            FileData fileData = fileDataListIdBack.get(fileDataListIdBack.size() - 1);
            coach.setIdCardBack(fileData.getRelativePath() + fileData.getFileName());
            coachDao.update(coach);
        }

        // 店铺图片信息
        fileDataService.saveFiles(request, picData_array, coach.getId(), ConstantUtil.FileUploadCode.Coach,
                0,0);
        return true;
    }

    public int checkCoach(Coach coach) {
        return coachDao.checkCoach(coach);
    }
}

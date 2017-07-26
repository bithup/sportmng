package com.xgh.mng.sercices;

import com.xgh.mng.basic.BaseService;
import com.xgh.mng.dao.ISubjectDao;
import com.xgh.mng.entity.FileData;
import com.xgh.mng.entity.Subject;
import com.xgh.mng.entity.SysIndustry;
import com.xgh.mng.entity.SysUnits;
import com.xgh.security.Base64Util;
import com.xgh.util.ConstantUtil;
import com.xgh.util.JSONUtil;
import com.xgh.util.JSONValidator;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2016/11/8 0008.
 */
@Service("subjectService")
public class SubjectServiceImpl extends BaseService implements ISubjectService {

    @Autowired
    protected ISubjectDao subjectDao;

    @Autowired
    protected IFileDataServiceNew fileDataService;

    public int add(Subject subject) {
        // TODO Auto-generated method stub
        return subjectDao.add(subject);
    }

    public int update(Subject subject) {
        // TODO Auto-generated method stub
        return subjectDao.update(subject);
    }

    public Subject get(long id) {
        Subject subject= subjectDao.get(id);
        return subject;
    }

    public List<Subject> getList(Map<String,Object> map){
        return subjectDao.getList(map);
    }

    public Map<String,Object> getGridList(HttpServletRequest request){
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> gridMap = new HashMap<String, Object>();
        String page = request.getParameter("page");
        String pagesize = request.getParameter("pagesize");
        map.put("page", Integer.parseInt(page));
        map.put("pagesize", Integer.parseInt(pagesize));
        map.put("unitId", getCurrentUnitId(request));
        List<Map<String,Object>> dataList = subjectDao.getListPage(map);
        if (dataList == null) {
            dataList = new ArrayList<Map<String, Object>>();
        }
        gridMap.put("Rows", dataList);
        gridMap.put("Total", subjectDao.getRows(map));
        return gridMap;
    }

    public int save(HttpServletRequest request,Subject subject) {
        if (subject != null && subject.getId() < 1) {

            subject = setSubjectImg(request, subject);
            SysUnits sysUnits = getCurrentUnits(request);
            SysIndustry sysIndustry = getCurrentIndustry(request);
            subject.setUnitId(sysUnits.getId());
            subject.setUnitCode(sysUnits.getUnitCode());
            subject.setInstId(sysIndustry.getId());
            subject.setInstCode(sysIndustry.getCode());
            subject.setUserId(getCurrentUserId(request));
            Date date = new Date();
            subject.setCreateDate(date);
            subject.setUpdateDate(date);
            subject.setStatus(1);
           return subjectDao.add(subject);
        } else {
            Subject subject1 = subjectDao.get(subject.getId());


            subject1.setSubjectName(subject.getSubjectName());
            subject1.setSubjectType(subject.getSubjectType());
            subject1.setRemark(subject.getRemark());
            subject1.setSubjectContent(subject.getSubjectContent());
            subject1 = setSubjectImg(request, subject1);
            subject1.setUpdateDate(new Date());
            subject1.setOrd(subject.getOrd());
            subject1.setSubjectUrl(subject.getSubjectUrl());

           return subjectDao.update(subject1);
        }


    }

    /**
     * 通过unitId获取subject Tree
     *
     * @param map
     * @return
     */


    /**
     * 添加类型图片
     *
     * @param request
     */
    private boolean addFileData(HttpServletRequest request,Subject subject) {
        String[] logoData_array = request.getParameterValues("logoData");
        //图片信息
        List<FileData> fileDataList = fileDataService.saveFiles(request, logoData_array, subject.getId(), ConstantUtil.FileUploadCode.Subject, 0,0);
        if (fileDataList != null && fileDataList.size() > 0) {
            FileData fileData = fileDataList.get(fileDataList.size() - 1);
            subject.setPath("/" + fileData.getRelativePath() + "/" + fileData.getFileName());
            String serverPath = ConstantUtil.SERVER_URL;
            subject.setRelativePath(serverPath+"/"+fileData.getRelativePath()+"/"+fileData.getFileName());
            subjectDao.update(subject);
        }
        return true;
    }
    /**
     * 通过unitId获取subject Tree
     *
     * @param map
     * @return
     */
    public List<Map<String, Object>> getSubjectTree(Map<String, Object> map) {
        return subjectDao.getSubjectTree(map);
    }

    private Subject setSubjectImg(HttpServletRequest request, Subject subject) {
        String _mobileImgData = request.getParameter("mobileImgData");
        String _androidImgData = request.getParameter("androidImgData");
        String _iosImgData = request.getParameter("iosImgData");
        String _pcImgData = request.getParameter("pcImgData");

        if (subject.getId() < 1) {
            //添加操作
            //存储位置
            String saveToPath = ConstantUtil.SAVE_PATH;
            subject.setPath(saveToPath);
        }

        if (JSONValidator.validate(_mobileImgData)) {
            Map<String, Object> mobileImgData = JSONUtil.getMap(_mobileImgData);
            if (mobileImgData.size() > 1) {
                subject.setMobileFilePath(copyFile(mobileImgData));
            }
        }

        if (JSONValidator.validate(_androidImgData)) {
            Map<String, Object> androidImgData = JSONUtil.getMap(_androidImgData);
            if (androidImgData.size() > 1) {
                subject.setAndroidFilePath(copyFile(androidImgData));
            }
        }

        if (JSONValidator.validate(_iosImgData)) {
            Map<String, Object> iosImgData = JSONUtil.getMap(_iosImgData);
            if (iosImgData.size() > 1) {
                subject.setIosFilePath(copyFile(iosImgData));
            }
        }

        if (JSONValidator.validate(_pcImgData)) {
            Map<String, Object> pcImgData = JSONUtil.getMap(_pcImgData);
            if (pcImgData.size() > 1) {
                subject.setPcFilePath(copyFile(pcImgData));
            }
        }

        return subject;
    }

    private String copyFile(Map<String, Object> map) {

        String srcPath = null;
        try {
            srcPath = Base64Util.decodeToString(map.get("savePath") + "");
            String fileName = map.get("fileName") + "";

            //存储位置
            String saveToPath = ConstantUtil.SAVE_PATH;
            //日期作为相对路径
            SimpleDateFormat formatdate = new SimpleDateFormat("yyyy/MM/dd/");
            String _relative_path = formatdate.format(new Date());

            String relative_path = "/" + ConstantUtil.FileUploadCode.Subject.value() + "/" + _relative_path + fileName;

            String destPath = saveToPath + relative_path;

            FileUtils.copyFile(new File(srcPath), new File(destPath));

            return relative_path;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}

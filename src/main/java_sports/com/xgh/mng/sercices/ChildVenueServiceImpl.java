package com.xgh.mng.sercices;

import com.xgh.mng.basic.BaseService;
import com.xgh.mng.dao.IChildVenueDao;
import com.xgh.mng.entity.ChildVenue;
import com.xgh.mng.entity.FileData;
import com.xgh.mng.entity.Venue;
import com.xgh.mng.services.IFileDataService;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/27.
 */
@Service("childVenueService")
public class ChildVenueServiceImpl extends BaseService implements IChildVenueService {

    @Autowired
    protected IChildVenueDao childVenueDao;

    @Autowired
    protected IFileDataServiceNew fileDataService;

    public ChildVenue get(long id) {
        return childVenueDao.get(id);
    }

    public List<Map<String, Object>> getList(HashMap<String,Object> map) {
        return childVenueDao.getList(map);
    }

    public int insert(ChildVenue childVenue) {
        return childVenueDao.insert(childVenue);
    }

    public int update(ChildVenue childVenue) {
        return childVenueDao.update(childVenue);
    }

    public int delete(long id) {
        return childVenueDao.delete(id);
    }

    public int save(HttpServletRequest request, ChildVenue childVenue) {
        long id = childVenue.getId();
        int flag = 0;
        if(id>0){
            ChildVenue childVenue1 = childVenueDao.get(id);
            childVenue.setCreateDate(childVenue1.getCreateDate());
            childVenue.setUpdateDate(new Date());
            if (addFileData(request, childVenue))
                return childVenueDao.update(childVenue);
            else
                return 0;
        }else{
            childVenue.setCreateDate(new Date());
            childVenue.setUpdateDate(new Date());
            childVenue.setStatus(0);
            flag = childVenueDao.insert(childVenue);
            if (addFileData(request, childVenue))
                return flag;
            else
                return 0;
        }
    }

    /**
     * 查询热门场馆
     * @param request
     * @return
     */
    public Map<String, Object> getGridList(HttpServletRequest request) {
        Map<String, Object> gridMap = new HashMap<String, Object>();
        Map<String,Object> map = new HashMap<String, Object>();
        int page = Integer.parseInt(request.getParameter("page"));
        int pagesize = Integer.parseInt(request.getParameter("pagesize"));
        String venueName = request.getParameter("venueName");
        String isRecommend = request.getParameter("isRecommend");
        if(null!=isRecommend&&!"".equals(isRecommend)){
            map.put("isRecommend",isRecommend);
        }
        map.put("page",page);
        map.put("pagesize",pagesize);
        map.put("venueName",venueName);
        map.put("unitId", getCurrentUnitId(request));
        map.put("instId", getCurrentInstId(request));
        List<Map<String,Object>> list = childVenueDao.getListPage(map);
        gridMap.put("Rows",list);
        gridMap.put("Total",childVenueDao.getRows(map));
        return gridMap;
    }

    /**
     * 添加场馆信息图片
     */
    private ChildVenue setSubjectImg(HttpServletRequest request, ChildVenue childVenue) {

        String venuePicData_ = request.getParameter("venuePicData");

        //存储位置
        String saveToPath = ConstantUtil.SAVE_PATH;

        if (JSONValidator.validate(venuePicData_)) {
            Map<String, Object> venuePicData = JSONUtil.getMap(venuePicData_);
            if (venuePicData.size() > 1) {
                childVenue.setPictureUrl(copyFile(venuePicData));
                childVenue.setPicRealPath(saveToPath+copyFile(venuePicData));
            }
        }

        return childVenue;
    }

    /**
     * 添加场馆图片
     *
     * @param request
     * @param
     */
    private boolean addFileData(HttpServletRequest request,ChildVenue childVenue) {
        String[] venuePicsData_array = request.getParameterValues("venuePicsData");
        String[] venuePicData_array = request.getParameterValues("venuePicData");

        // 店铺图片信息
        fileDataService.saveFiles(request, venuePicsData_array, childVenue.getId(), ConstantUtil.FileUploadCode.ChildVenue,
                0,0);
        List<FileData> venuePicList = fileDataService.saveFiles(request, venuePicData_array, childVenue.getId(), ConstantUtil.FileUploadCode.ChildVenue,
                0,1);
        if (venuePicList != null && venuePicList.size() > 0) {
            FileData fileData = venuePicList.get(venuePicList.size() - 1);
            String serverPath = ConstantUtil.SERVER_URL;
            childVenue.setPictureUrl( fileData.getRelativePath() + fileData.getFileName());
            childVenue.setPicRealPath(serverPath + fileData.getRelativePath() + fileData.getFileName());
            childVenueDao.update(childVenue);
        }
        return true;
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

            String relative_path = "/" + ConstantUtil.FileUploadCode.ChildVenue.value() + "/" + _relative_path + fileName;

            String destPath = saveToPath + relative_path;

            FileUtils.copyFile(new File(srcPath), new File(destPath));

            return relative_path;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}

package com.xgh.mng.sercices;

import com.xgh.mng.basic.BaseService;
import com.xgh.mng.dao.IKindsDao;
import com.xgh.mng.entity.FileData;
import com.xgh.mng.entity.Kinds;
import com.xgh.mng.entity.SysUnits;
import com.xgh.mng.services.IFileDataService;
import com.xgh.util.ConstantUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


@Service("kindsService")
public class KindsServiceImpl extends BaseService implements IKindsService {


    private Logger logger = Logger.getLogger(KindsServiceImpl.class);

    @Autowired
    protected IKindsDao kindsDao;

    @Autowired
    protected IFileDataServiceNew fileDataService;

    /**
     * Add data, pay attention to the path of the gain of algorithm
     *
     * @param kinds
     */
    public int add(Kinds kinds) {
        // TODO Auto-generated method stub

        return kindsDao.add(kinds);
    }

    public int update(Kinds kinds) {
        // TODO Auto-generated method stub
        return kindsDao.update(kinds);
    }

    public int delete(long id) {
        // TODO Auto-generated method stub
        return kindsDao.deleteById(id);
    }

    public Kinds get(long id) {
        // TODO Auto-generated method stub
        return kindsDao.get(id);
    }

    public List<Kinds> getList(Kinds kinds) {

        return kindsDao.getList(kinds);
    }


    /**
     * getListPage
     *
     * @return
     */
    public List<Kinds> getListPage(Map<String, Object> map) {

        return kindsDao.getListPage(map);
    }

    /**
     * getRows
     *
     * @param map
     * @return
     */
    public long getRows(Map<String, Object> map) {

        return kindsDao.getRows(map);
    }

    /**
     * 获取列表数据
     *
     * @param request
     * @return
     */
    public Map<String, Object> getGrid(HttpServletRequest request) {
        Map<String, Object> gridMap = new HashMap<String, Object>();

        Map<String, Object> map = new HashMap<String, Object>();
        List<Kinds> dataList = getListPage(map);
        long totalRows = getRows(map);

        gridMap.put("Rows", dataList);
        gridMap.put("Total", totalRows);
        return null;
    }

    /**
     * 通过 parenId 获取子级的分类
     *
     * @param request
     * @param parentId
     * @return
     */
    public List<Map<String, Object>> getChildTreeData(HttpServletRequest request, long parentId) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("instId", getCurrentInstId(request));
        paramMap.put("parentId", Long.valueOf(parentId));
        List<Map<String, Object>> list = kindsDao.getChildTreeData(paramMap);
        return list;
    }



    /**
     * 通过parentId获取
     * @param request
     * @param
     * @return
     */
    public List<Kinds> getChildData(HttpServletRequest request, long parentId){
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("instId", getCurrentInstId(request));
        map.put("parentId",parentId);
        List<Kinds> list= kindsDao.getChildData(map);
        return list;
    }

    /**
     * 保存kinds
     *
     * @param request
     * @param kinds
     * @param op
     * @return
     */
    public int save(HttpServletRequest request, Kinds kinds, String op) {
        SysUnits sysUnits = getCurrentUnits(request);
        if ("modify".equals(op)) {
            kinds.setUpdateDate(new Date());
            kinds.setStatus(1);
            kinds.setZoneCode(sysUnits.getZoneCode());
            kindsDao.update(kinds);
        } else {
            kinds.setUserId(getCurrentUserId(request));//用户id
            kinds.setInstId(getCurrentInstId(request));//instId=10014行业编码
            kinds.setInstCode(getCurrentIndustry(request).getCode());//行业编号
            Date date = new Date();
            kinds.setCreateDate(date);
            kinds.setUpdateDate(date);
            kinds.setStatus(1);
            kinds.setZoneCode(sysUnits.getZoneCode());
            kindsDao.add(kinds);
        }
        Kinds pKinds = kindsDao.get(kinds.getParentId());
        if (pKinds != null)
            kinds.setPreCode(pKinds.getPreCode() + "_" + kinds.getId());
        else
            kinds.setPreCode(kinds.getId() + "");
         kindsDao.update(kinds);
        if (addFileData(request, kinds)) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * 获取列表数据
     *
     * @param request
     * @param parentId
     * @return
     */
    public Map<String, Object> getGridData(HttpServletRequest request, long parentId) {
        String page = request.getParameter("page");
        String pagesize = request.getParameter("pagesize");
        HashMap map = new HashMap();
        long unitId = getCurrentInstId(request);
        //分页
        map.put("parentId", Long.valueOf(parentId));
        map.put("instId", Long.valueOf(unitId));
        map.put("page", Integer.valueOf(Integer.parseInt(page)));
        map.put("pagesize", Integer.valueOf(Integer.parseInt(pagesize)));
        HashMap gridData = new HashMap();
        Object dataList = this.kindsDao.getListPage(map);
        if (dataList == null) {
            dataList = new ArrayList();
        }
        gridData.put("Rows", dataList);
        gridData.put("Total", this.kindsDao.getRows(map));
        logger.info(gridData);
        return gridData;
    }

    /**
     * 判断是否有子节点
     *
     * @param kinds
     * @return
     */
    public boolean isHasChild(Kinds kinds) {

        return kindsDao.isHasChild(kinds.getId()) > 0L;
    }

    /**
     * 添加类型图片
     *
     * @param request
     * @param kinds
     */
    private boolean addFileData(HttpServletRequest request, Kinds kinds) {
        String[] logoData_array = request.getParameterValues("logoData");
        // Logo图片信息
        List<FileData> fileDataList = fileDataService.saveFiles(request, logoData_array, kinds.getId(), ConstantUtil.FileUploadCode.Kinds,
                0,1);
        if (fileDataList != null && fileDataList.size() > 0) {
            FileData fileData = fileDataList.get(fileDataList.size() - 1);
            kinds.setSportUrl("/" + fileData.getRelativePath() + "/" + fileData.getFileName());
            String serverPath = ConstantUtil.SERVER_URL;
            kinds.setSportRealUrl(serverPath+"/"+fileData.getRelativePath()+"/"+fileData.getFileName());
            kindsDao.update(kinds);
        }
        return true;
    }
}
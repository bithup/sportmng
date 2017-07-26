package com.xgh.mng.sercices;

import com.xgh.mng.basic.BaseService;
import com.xgh.mng.dao.IRecommendDao;
import com.xgh.mng.entity.Recommend;
import com.xgh.mng.services.IFileDataService;
import com.xgh.util.ConstantUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by scx on 2016/2/25.
 */
@Service("recommendService")
public class RecommendServiceImpl extends BaseService implements IRecommendService {

    @Autowired
    protected IRecommendDao recommendDao;
    @Autowired
    protected IFileDataService fileDataService;

    public int add(Recommend recommend) {
        return recommendDao.add(recommend);
    }

    public int update(Recommend recommend) {
        return recommendDao.update(recommend);
    }

    public int save(HttpServletRequest request, Recommend recommend) {

        //此处认为shop不为空，进行操作，是否为空的判断在controller中进行
        if (recommend != null && recommend.getId() < 1) {

            recommend.setStatus(1);

            Date date = new Date();

           recommend.setUpdateDate(date);
            int a = add(recommend);
            int b = 0;
            if (addFileData(request, recommend))
                b = 1;
            if (a == 1 && b == 1)
                return 1;
            else
                return -1;
        } else {
            recommend.setStatus(1);
            Date date = new Date();
            recommend.setUpdateDate(date);
            int a = update(recommend);
            int b = 0;
            if (addFileData(request, recommend))
                b = 1;
            if (a == 1 && b == 1)
                return 1;
            else
                return -1;
        }
    }


    public int delete(long id) {
        return recommendDao.deleteById(id);
    }

    public Recommend get(long id) {
        return recommendDao.get(id);
    }

    public List<Recommend> getList(Map<String, Object> map) {
        return recommendDao.getList(map);
    }

    public Map<String, Object> getGridList(HttpServletRequest request) {
        String page = request.getParameter("page");
        String pagesize = request.getParameter("pagesize");
        String kind = request.getParameter("kind");


        Map<String, Object> map = new HashMap<String, Object>();

        map.put("page", Integer.parseInt(page));
        map.put("pageSize", Integer.parseInt(pagesize));
        map.put("kind", kind);

        Map<String, Object> gridMap = new HashMap<String, Object>();
        List<Recommend> dataList = recommendDao.getListPage(map);
        if (dataList == null) {
            dataList = new ArrayList<Recommend>();
        }
        gridMap.put("Rows", dataList);
        gridMap.put("Total", recommendDao.getRows(map));
        return gridMap;
    }


    private boolean addFileData(HttpServletRequest request, Recommend recommend) {


        String[] picData_array = request.getParameterValues("picData");


        // 图片信息
        fileDataService.saveFiles(request, picData_array, recommend.getId(), ConstantUtil.FileUploadCode.Active,
                0);
        return true;
    }

}

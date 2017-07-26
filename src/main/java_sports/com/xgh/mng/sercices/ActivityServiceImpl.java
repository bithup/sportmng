package com.xgh.mng.sercices;

import com.xgh.mng.basic.BaseService;
import com.xgh.mng.dao.IActivityDao;
import com.xgh.mng.dao.IZoneDao;
import com.xgh.mng.entity.Activity;
import com.xgh.mng.entity.FileData;
import com.xgh.mng.entity.SysUnits;
import com.xgh.mng.entity.Zone;
import com.xgh.util.ConstantUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

/**
 * ActivityService Impl
 *
 * @author h2y
 *
 * @time:2016-11-01
 *
 * @Email:
 */
@Service("activityService")
public class ActivityServiceImpl extends BaseService implements IActivityService{

	@Autowired
	protected IActivityDao activityDao;

	@Autowired
	protected IFileDataServiceNew fileDataService;

	@Autowired
	protected IZoneDao zoneDao;

	/**
	 * Add data, pay attention to the path of the gain of algorithm
	 * 
	 * @param activity
	 *
	 */
	public int add(Activity activity) {
		// TODO Auto-generated method stub
		return activityDao.add(activity);
	}

	public int update(Activity activity) {
		// TODO Auto-generated method stub
		return activityDao.update(activity);
	}

	public int save(HttpServletRequest request, Activity activity){
		Zone zone = zoneDao.get(activity.getData1());
		if(activity!=null && activity.getId()<1){
			SysUnits sysUnits = getCurrentUnits(request);
			activity.setUnitId(sysUnits.getId());
			activity.setUserId(getCurrentUserId(request));
			activity.setStatus(1);
			activity.setData9(zone.getCode());
			activity.setActivityType(1);
			Date date = new Date();
			activity.setCreateDate(date);
			activity.setUpdateDate(date);

			int flag = activityDao.add(activity);
			if (addFileData(request,activity)) {
				return flag;
			} else {
				return 0;
			}
		}else{
			Activity activity1 = activityDao.get(activity.getId());
			activity.setUnitId(getCurrentUnitId(request));
			activity.setUserId(getCurrentUserId(request));
			activity.setOrd(activity.getOrd());
			activity.setStatus(1);
			activity.setData9(zone.getCode());
			activity.setCreateDate(activity1.getCreateDate());
			activity.setUpdateDate(new Date());
			if (addFileData(request,activity)) {
				return activityDao.update(activity);
			} else {
				return 0;
			}
		}

	}
	
	public int delete(long id) {
		return activityDao.deleteById(id);
	}

	public Activity get(long id) {
		Activity activity = activityDao.get(id);
		String activityPath = ConstantUtil.SERVER_URL+activity.getActivityPath();
		activity.setActivityPath(activityPath);
		return activity;
	}

	public List<Activity> getList(Map<String,Object> map){
		return activityDao.getList(map);
	}
	
	public Map<String,Object> getGridList(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> gridMap = new HashMap<String, Object>();
		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		String activityName = request.getParameter("activityName");
		String isRecommend = request.getParameter("isRecommend");
		String isCheck = request.getParameter("isCheck");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		map.put("startTime",startTime);
		map.put("endTime",endTime);
		map.put("isCheck",isCheck);
		map.put("isRecommend",isRecommend);
		map.put("activityName",activityName);
		map.put("unitId", getCurrentUnitId(request));
		map.put("page", Integer.parseInt(page));
		map.put("pagesize", Integer.parseInt(pagesize));
		List<Map<String,Object>> dataList = activityDao.getListPage(map);
		if (dataList == null) {
			dataList = new ArrayList<Map<String, Object>>();
		}
		for(Map<String,Object> dataList2:dataList){
			if (String.valueOf(dataList2.get("activityType"))!=null) {
				if (Integer.parseInt(String.valueOf(dataList2.get("activityType"))) == 0) {
					dataList2.put("activityType", "个人");
				} else if (Integer.parseInt(String.valueOf(dataList2.get("activityType"))) == 1) {
					dataList2.put("activityType", "团体");
				}
			}
			if (String.valueOf(dataList2.get("isRecommend"))!=null) {
				if (Integer.parseInt(String.valueOf(dataList2.get("isRecommend"))) == 0) {
					dataList2.put("isRecommend", "否");
				} else if (Integer.parseInt(String.valueOf(dataList2.get("isRecommend"))) == 1) {
					dataList2.put("isRecommend", "是");
				}
			}
			if (String.valueOf(dataList2.get("isCheck"))!=null) {
				if (Integer.parseInt(String.valueOf(dataList2.get("isCheck"))) == 0) {
					dataList2.put("isCheck", "未审核");
				} else if (Integer.parseInt(String.valueOf(dataList2.get("isCheck"))) == 1) {
					dataList2.put("isCheck", "通过");
				} else if (Integer.parseInt(String.valueOf(dataList2.get("isCheck"))) == 2) {
					dataList2.put("isCheck", "未通过");
				}
			}
		}
		gridMap.put("Rows", dataList);
		gridMap.put("Total", activityDao.getRows(map));
		return gridMap;
	}

	/**
	 * 活动审核
	 * @param activity
	 * @return
	 */
	public int checkActivity(Activity activity) {
		return activityDao.checkActivity(activity);
	}

	/**
	 * 获取活动树
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getActivityTree(Map<String,Object> map){
		return activityDao.getActivityTree(map);
	}

	/**
	 * 添加类型图片
	 *
	 * @param request
	 */
	private boolean addFileData(HttpServletRequest request,Activity activity) {
		String[] logoData_array = request.getParameterValues("logoData");
		String[] picData_array = request.getParameterValues("picData");
		//图片信息
		List<FileData> fileDataList = fileDataService.saveFiles(request, logoData_array, activity.getId(), ConstantUtil.FileUploadCode.Active,
				0,0);
		if (fileDataList != null && fileDataList.size() > 0) {
			FileData fileData = fileDataList.get(fileDataList.size() - 1);
			activity.setActivityPath("/" + fileData.getRelativePath() + "/" + fileData.getFileName());
			String serverPath = ConstantUtil.SERVER_URL;
			activity.setActivityRealPath(serverPath+"/"+fileData.getRelativePath()+"/"+fileData.getFileName());
			activityDao.update(activity);
		}
		fileDataService.saveFiles(request, picData_array, activity.getId(), ConstantUtil.FileUploadCode.Active,
				0,0);
		return true;
	}
}
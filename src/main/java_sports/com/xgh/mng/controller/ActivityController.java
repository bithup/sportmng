package com.xgh.mng.controller;

import com.xgh.mng.basic.BaseController;
import com.xgh.mng.entity.Activity;
import com.xgh.mng.entity.Kinds;
import com.xgh.mng.sercices.IActivityService;
import com.xgh.mng.sercices.IFileDataServiceNew;
import com.xgh.mng.sercices.IKindsService;
import com.xgh.mng.services.IZoneService;
import com.xgh.util.ConstantUtil;
import com.xgh.util.JSONUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ActivityController
 *
 * @author h2y
 *
 * @time:2016-01-23 11:48:18
 *
 * @Email:
 */

@Controller
@Scope("prototype")
@RequestMapping(value="/business/activity/")
public class ActivityController extends BaseController {
    /**
     * @Fields serialVersionUID :
     */
    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(ActivityController.class);

    @Autowired
    protected IActivityService activityService;

    @Autowired
    protected IKindsService kindsService;

    @Autowired
    protected IZoneService zoneService;

    @Autowired
    protected IFileDataServiceNew fileDataService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   //true:允许输入空值，false:不能为空值
    }

    @InitBinder("activity")
    public void initBinder2(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("activity.");
    }

    @RequestMapping(value = "init")
    public ModelAndView init() {
        return getModelAndView("business/activity/init");
    }

    @RequestMapping(value = "/getList")
    public void getList() {
        logger.info("getList");
        outJson(activityService.getGridList(request));
    }

    @RequestMapping(value = "/add")
    public ModelAndView add(@ModelAttribute Activity activity, @RequestParam String op) {
        ModelAndView view = new ModelAndView();
        view.addObject("op", op);
        if ("modify".equals(op)) {
            activity = activityService.get(activity.getId());
            long kindsId = activity.getSportId();
            Kinds kinds = kindsService.get(kindsId);
            if (kinds != null) {
                String preCode = kinds.getPreCode();
                view.addObject("preCode", preCode);
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("instId", getCurrentInstId());
                params.put("dataId", activity.getId());
                params.put("dataCode", ConstantUtil.FileUploadCode.Active.value());
                params.put("dataVersion", 0);
                params.put("server", ConstantUtil.SERVER_URL);
                List<Map<String, Object>> fileDataList = fileDataService.getFileDatas(params);
                view.addObject("fileDataListJson", JSONUtil.getJson(fileDataList));
            }
            String parFix = "";
            if(activity.getData1()>0){
                parFix = zoneService.get(activity.getData1()).getPreFix();
            }
            view.addObject("parFix",parFix);
        }
        view.addObject("activity", activity);
        view.setViewName("business/activity/add");
        return view;
    }

    @RequestMapping(value = "/save")
    public void save(@ModelAttribute Activity activity) {
        logger.info("save");
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (activity != null) {
            //判断添加修改,aom, 0 添加,1 修改
            int aom = activity.getId() > 1 ? 1 : 0;
            String introduce = activity.getActivityIntroduce();
            String declare = activity.getDeclares();
            introduce = introduce.replace("<p>", "");
            introduce = introduce.replace("</p>", "");
            introduce = introduce.replace("<br />", "");

            declare = declare.replace("<p>", "");
            declare = declare.replace("</p>", "");
            declare = declare.replace("<br />", "");

            activity.setActivityIntroduce(introduce);
            activity.setDeclares(declare);

            int flg = activityService.save(request,activity);

            if (flg == 1) {
                if (aom == 0) {
                    resultMap = getResultMap("1", "添加成功!");
                } else
                    resultMap = getResultMap("1", "修改成功!");
            } else {
                if (aom == 0) {
                    resultMap = getResultMap("0", "添加失败!");
                } else
                    resultMap = getResultMap("0", "修改失败!");
            }
        } else
            resultMap = getResultMap("0", "数据初始化失败!");
        outJson(resultMap);
    }

    @RequestMapping(value = "/delete")
    public void delete(@ModelAttribute Activity activity) {
        logger.info("delete");
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Activity activity2 = activityService.get(activity.getId());
        activity2.setUpdateDate(new Date());
        activity2.setStatus(-1);
        int flg = activityService.update(activity2);
        if (flg == 1) {
            resultMap = getResultMap("1", "删除成功!");
        } else
            resultMap = getResultMap("0", "删除失败!");
        outJson(resultMap);
    }

    /**
     * 审核活动
     * @param activity
     * @return
     */
    @RequestMapping(value = "checkActivity")
    public void checkActivity(@ModelAttribute Activity activity){
        HashMap<String,Object> resultMap = new HashMap<String, Object>();
        activity.setUpdateDate(new Date());
        int flag = activityService.checkActivity(activity);
        if (flag > 0) {
            resultMap.put("success", true);
            resultMap.put("msg", "审核成功");
        } else {
            resultMap.put("success", false);
            resultMap.put("msg", "审核失败");
        }
        outJson(resultMap);
    }

    @RequestMapping(value = "recommendActivity")
    public ModelAndView recommendActivity(){
        ModelAndView view = new ModelAndView();
        view.setViewName("sports/recommend/activity");
        view.addObject("toolbar", sysButtonService.getMenuButtons(request, getCurrentSysRoleId(), getCurrentUser()));
        view.addObject("gridComluns", this.sysGridColumnsService.getGridColumsByRequest(this.request));
        return view;
    }


    @RequestMapping(value="update")
    public void update(){
        long id  = Long.parseLong(request.getParameter("id"));
        int isRecommend = Integer.parseInt(request.getParameter("isRecommend"));
        Activity activity = activityService.get(id);
        activity.setIsRecommend(isRecommend);
        int flag = activityService.update(activity);
        HashMap<String,Object> resultMap = new HashMap<String, Object>();
        if(flag>0){
            resultMap.put("code",1);
            resultMap.put("msg","操作成功！");
        }else {
            resultMap.put("code",0);
            resultMap.put("msg","操作失败！");
        }
        outJson(resultMap);

    }

    @RequestMapping(value="getListSelect")
    public ModelAndView getListSelect(){
        ModelAndView view = getModelAndView("sports/dialog/activitySelect");
        return view;
    }

    @RequestMapping(value = "/getListZone")
    public void getListZone(@RequestParam long parentId) {
        List list = this.zoneService.getTreeList(parentId, "0", 1);
        outJson(list);
    }

}

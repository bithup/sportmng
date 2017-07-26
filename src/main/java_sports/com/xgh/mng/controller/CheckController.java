package com.xgh.mng.controller;

import com.xgh.mng.basic.BaseController;
import com.xgh.mng.entity.Activity;
import com.xgh.mng.entity.Coach;
import com.xgh.mng.entity.Kinds;
import com.xgh.mng.entity.Venue;
import com.xgh.mng.sercices.IActivityService;
import com.xgh.mng.sercices.ICoachService;
import com.xgh.mng.sercices.IKindsService;
import com.xgh.mng.sercices.IVenueService;
import com.xgh.util.ConstantUtil;
import com.xgh.util.JSONUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * CheckController
 *
 * @author h2y
 *
 * @time:2016-11-01
 *
 * @Email:
 */
@Controller
@Scope("prototype")
@RequestMapping(value="/business/check/")
public class CheckController extends BaseController {
	/**
	 * @Fields serialVersionUID : 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(CheckController.class);

    @Autowired
    protected IActivityService activityService;

    @Autowired
    protected IKindsService kindsService;

    @Autowired
    protected ICoachService coachService;

    @Autowired
    protected IVenueService venueService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    /**
     * 场馆审核
     * @return
     */
    @RequestMapping(value = "checkVenueInit")
    public ModelAndView checkVenueInit(){
        return getModelAndView("business/check/checkVenueInit");
    }
    /**
     * 场馆审核详情
     * @return
     */
    @RequestMapping(value = "venueDetail")
    public ModelAndView venueDetail(){
        ModelAndView view = new ModelAndView();
        view.setViewName("business/check/venueDetail");
        HashMap<String,Object> params = new HashMap<String, Object>();
        String venueId = request.getParameter("id");
        Venue venue = venueService.get(Long.parseLong(venueId));
        params.put("instId", getCurrentInstId());
        params.put("dataId", venue.getId());
        params.put("dataCode", ConstantUtil.FileUploadCode.Venue.value());
        params.put("dataVersion", 0);
        params.put("server", ConstantUtil.SERVER_URL);
        view.addObject("fileDataListJson", JSONUtil.getJson(fileDataService.getFileDatas(params)));
        view.addObject("venue",venue);
        return view;
    }

    /**
     * 教练审核
     * @return
     */
    @RequestMapping(value = "checkCoachInit")
    public ModelAndView checkCoachInit(){
        return getModelAndView("business/check/checkCoachInit");
    }
    /**
     * 教练审核详情
     * @return
     */
    @RequestMapping(value = "coachDetail")
    public ModelAndView coachDetail(){
        ModelAndView view = new ModelAndView();
        view.setViewName("business/check/coachDetail");
        HashMap<String,Object> params = new HashMap<String, Object>();
        String coachId = request.getParameter("id");
        System.out.println(coachId);
        Coach coach = coachService.get(Long.parseLong(coachId));
        params.put("instId", getCurrentInstId());
        params.put("dataId", coach.getId());
        params.put("dataCode", ConstantUtil.FileUploadCode.Coach.value());
        params.put("dataVersion", 0);
        params.put("server", ConstantUtil.SERVER_URL);
        view.addObject("fileDataListJson", JSONUtil.getJson(fileDataService.getFileDatas(params)));
        view.addObject("coach",coach);
        return view;
    }

    /**
     * 活动审核
     * @return
     */
    @RequestMapping(value = "checkActivityInit")
    public ModelAndView checkActivityInit(){
        return getModelAndView("business/check/checkActivityInit");
    }
    /**
     * 活动审核详情
     * @return
     */
    @RequestMapping(value = "activityDetail")
    public  ModelAndView activityDetail(){
        ModelAndView view  = new ModelAndView();
        view.setViewName("business/check/activityDetail");
        HashMap<String,Object> params = new HashMap<String, Object>();
        String activityId = request.getParameter("id");
        Activity activity = activityService.get(Long.parseLong(activityId));
        Kinds kinds = kindsService.get(Long.parseLong(String.valueOf(activity.getSportId())));
        if(activity.getVenueId()>0){
            Venue venue = venueService.get(Long.parseLong(String.valueOf(activity.getVenueId())));
            view.addObject("venue",venue);
        }

        params.put("instId", getCurrentInstId());
        params.put("dataId", activity.getId());
        params.put("dataCode", ConstantUtil.FileUploadCode.Active.value());
        params.put("dataVersion", 0);
        params.put("server", ConstantUtil.SERVER_URL);
        view.addObject("fileDataListJson", JSONUtil.getJson(fileDataService.getFileDatas(params)));
        view.addObject("activity",activity);
        view.addObject("kinds",kinds);
        return view;
    }



}
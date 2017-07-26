package com.xgh.mng.controller;

import com.xgh.mng.basic.BaseController;
import com.xgh.mng.entity.Venue;
import com.xgh.mng.sercices.IFileDataServiceNew;
import com.xgh.mng.sercices.IVenueService;
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

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/19.
 */

@Controller
@Scope("prototype")
@RequestMapping(value = "/sport/venue")
public class VenueController extends BaseController{

    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(KindsController.class);

    @Autowired
    protected IVenueService venueService;

    @Autowired
    protected IZoneService zoneService;

    @Autowired
    protected IFileDataServiceNew fileDataService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd ");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @InitBinder("venue")
    public void initBinder2(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("venue.");
    }

    @RequestMapping(value="init")
    public ModelAndView init(){
        ModelAndView view = new ModelAndView();
        view.setViewName("sports/venue/init");
        view.addObject("toolbar", sysButtonService.getMenuButtons(request, getCurrentSysRoleId(), getCurrentUser()));
        view.addObject("gridComluns", this.sysGridColumnsService.getGridColumsByRequest(this.request));
        return view;

    }

    @RequestMapping(value="getList")
    public void getList(){
        HashMap<String,Object> map = new HashMap<String, Object>();
        String name = request.getParameter("name");
        if(null!=name&&!"".equals(name)) {
            try {
                name = new String(name.getBytes("ISO8859-1"), "UTF-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String isCheck = request.getParameter("isCheck");
        map.put("isCheck",isCheck);
        int page =Integer.parseInt(request.getParameter("page"));
        int pagesize = Integer.parseInt(request.getParameter("pagesize"));
        map.put("name",name);
        map.put("page",page);
        map.put("pagesize",pagesize);
        Map<String,Object> resultMap = venueService.getListPage(map);
        outJson(resultMap);
    }

    @RequestMapping(value="add")
    public ModelAndView add(@ModelAttribute Venue venue, @RequestParam String op){
        ModelAndView view  = new ModelAndView();
        view.setViewName("sports/venue/add");
        view.addObject("op",op);
        if("modify".equals(op)){
            venue = venueService.get(venue.getId());
            String parFix = zoneService.get(venue.getZoneId()).getPreFix();
            view.addObject("parFix",parFix);
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("instId", getCurrentInstId());
            params.put("dataId", venue.getId());
            params.put("dataCode",ConstantUtil.FileUploadCode.Venue.value());
            params.put("dataVersion", 0);
            params.put("server", ConstantUtil.SERVER_URL);
            List<Map<String, Object>> fileDataList = fileDataService.getFileDatas(params);
            view.addObject("fileDataListJson", JSONUtil.getJson(fileDataList));

            view.addObject(venue);
        }
        return view;
    }

    @RequestMapping(value="save")
    public void save(@ModelAttribute Venue venue){
        HashMap<String,Object> resultMap = new HashMap<String, Object>();
        long id = venue.getId();
        String introduce = venue.getIntroduction();
        introduce = introduce.replace("<p>", "");
        introduce = introduce.replace("</p>", "");
        introduce = introduce.replace("<br />", "");
        venue.setIntroduction(introduce);
        int flag = venueService.save(request,venue);
        if(id>0){
            if(flag>0){
                resultMap.put("code",1);
                resultMap.put("msg","修改成功！");
            }else{
                resultMap.put("code",0);
                resultMap.put("msg","修改失败！");
            }
        }else{
            if(flag==1){
                resultMap.put("code",1);
                resultMap.put("msg","添加成功！");
            }else if(flag==2){
                resultMap.put("code",0);
                resultMap.put("msg","此电话号码已经被注册，请更换！");
            }
            else{
                resultMap.put("code",0);
                resultMap.put("msg","添加失败！");
            }
        }
        outJson(resultMap);

    }

    @RequestMapping(value="delete")
    public void delete(){
        long id = Long.parseLong(request.getParameter("id"));
        HashMap<String,Object> resultMap = new HashMap<String, Object>();
        Venue venue = venueService.get(id);
        venue.setStatus(-1);
        int flag = venueService.update(venue);//逻辑删除
        if(flag>0){
            resultMap.put("code",1);
            resultMap.put("msg","删除成功");
        }else{
            resultMap.put("code",0);
            resultMap.put("msg","删除失败");
        }
        outJson(resultMap);
    }

    @RequestMapping(value = "/getListZone")
    public void getListZone(@RequestParam long parentId) {
        List list = this.zoneService.getTreeList(parentId, "0", 1);
        outJson(list);
    }

    /**
     * 获取场馆下拉框信息
     * @param
     */
    @RequestMapping(value = "getVenue")
    public void getVenue(@RequestParam long instId){
        List list = this.venueService.getVenue(instId);
        outJson(list);
    }

    /**
     * 场馆审核
     * @param venue
     */
    @RequestMapping(value = "checkVenue")
    public void checkVenue(@ModelAttribute Venue venue){
        HashMap<String,Object> resultMap = new HashMap<String, Object>();
        venue.setUpdateDate(new Date());
        int flag = venueService.checkVenue(venue);
        if (flag>0){
            resultMap.put("success",true);
            resultMap.put("msg","审核成功");
        }else {
            resultMap.put("success",false);
            resultMap.put("msg","审核失败");
        }
        outJson(resultMap);
    }
}

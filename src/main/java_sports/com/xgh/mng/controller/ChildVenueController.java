package com.xgh.mng.controller;

import com.xgh.mng.basic.BaseController;
import com.xgh.mng.entity.ChildVenue;
import com.xgh.mng.entity.Kinds;
import com.xgh.mng.sercices.IChildVenueService;
import com.xgh.mng.sercices.IFileDataServiceNew;
import com.xgh.mng.sercices.IKindsService;
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

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/27.
 */
@Controller
@Scope("")
@RequestMapping("/sport/childVenue/")
public class ChildVenueController extends BaseController{

    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(ChildVenueController.class);

    @Autowired
    protected IChildVenueService childVenueService;

    @Autowired
    protected IKindsService kindsService;

    @Autowired
    protected IFileDataServiceNew fileDataService;


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd ");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   //true:允许输入空值，false:不能为空值
    }

    @InitBinder("childVenue")
    public void initBinder2(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("childVenue.");
    }

    @RequestMapping(value="/init")
    public ModelAndView init(){
        ModelAndView view = new ModelAndView();
        view.setViewName("sports/childVenue/init");
        long parentId = Long.parseLong(request.getParameter("id"));
        view.addObject("parentId",parentId);
        return view;
    }

    @RequestMapping(value="/getList")
    public void getList(){
        HashMap<String,Object> map = new HashMap<String, Object>();
        long parentId = Long.parseLong(request.getParameter("parentId"));
        map.put("parentId",parentId);
        List<Map<String,Object>> list = childVenueService.getList(map);
        HashMap<String,Object> gridMap = new HashMap<String, Object>();
        gridMap.put("Rows",list);
        outJson(gridMap);
    }

    @RequestMapping(value="/add")
    public ModelAndView add(@ModelAttribute ChildVenue childVenue,@RequestParam String op){
        ModelAndView view = new ModelAndView();
        view.setViewName("sports/childVenue/add");
        view.addObject("op",op);
        if(null!=request.getParameter("parentId")&&!"".equals(request.getParameter("parentId"))){
            long parentId = Long.parseLong(request.getParameter("parentId"));
            view.addObject("parentId",parentId);
        }
       if(op.equals("modify")){
           childVenue = childVenueService.get(childVenue.getId());
           long kindsId = childVenue.getSportId();
           Kinds kinds = kindsService.get(kindsId);
           if (kinds != null)
           {String preCode = kinds.getPreCode();
               view.addObject("preCode", preCode);}

           Map<String, Object> params = new HashMap<String, Object>();
           params.put("instId", getCurrentInstId());
           params.put("dataId", childVenue.getId());
           params.put("dataCode", ConstantUtil.FileUploadCode.ChildVenue.value());
           params.put("dataVersion", 0);
           params.put("server", ConstantUtil.SERVER_URL);
           view.addObject("fileDataListJson", JSONUtil.getJson(fileDataService.getFileDatas(params)));
       }
        view.addObject("childVenue",childVenue);
        return view;
    }

    @RequestMapping(value="save")
    public void save(@ModelAttribute ChildVenue childVenue){
        HashMap<String,Object> resultMap = new HashMap<String, Object>();
        long id = childVenue.getId();
        int flag = childVenueService.save(request,childVenue);
        if(id>0){//修改
            if(flag>0){
                resultMap.put("code",1);
                resultMap.put("msg","修改成功");
            }else {
                resultMap.put("code",0);
                resultMap.put("msg","修改失败");
            }
        }else{//添加
            if(flag>0){
                resultMap.put("code",1);
                resultMap.put("msg","添加成功");
            }else {
                resultMap.put("code",0);
                resultMap.put("msg","添加失败");
            }
        }
        outJson(resultMap);
    }

    @RequestMapping(value = "delete")
    public void delete(){
        HashMap<String,Object> resultMap = new HashMap<String, Object>();
        long id = Long.parseLong(request.getParameter("id"));
        ChildVenue venue = childVenueService.get(id);
        venue.setStatus(-1);
        //逻辑删除
        int flag = childVenueService.update(venue);
        if(flag>0){
            resultMap.put("code",1);
            resultMap.put("msg","删除成功");
        }else {
            resultMap.put("code",0);
            resultMap.put("msg","删除失败");
        }
        outJson(resultMap);
    }

    /**
     * 查询运动分类
     *
     * @param parentId
     */
    @RequestMapping(value = "getListType")
    public void getListType(@RequestParam long parentId) {
        List list = this.kindsService.getChildTreeData(request, parentId);
        outJson(list);
    }

    @RequestMapping(value = "getListPage")
    public ModelAndView getListPage(){
        ModelAndView view = new ModelAndView();
        view.setViewName("sports/recommend/init");
        view.addObject("toolbar", sysButtonService.getMenuButtons(request, getCurrentSysRoleId(), getCurrentUser()));
        view.addObject("gridComluns", this.sysGridColumnsService.getGridColumsByRequest(this.request));
        return view;
    }

    /**
     * 热门场馆管理
     */
    @RequestMapping(value = "getListRecommend")
    public void getListRecommend(){
        outJson(childVenueService.getGridList(request));
    }

    /**
     * 热门场馆设置
     */
    @RequestMapping(value="update")
    public void update(){
        long id  = Long.parseLong(request.getParameter("id"));
        int isRecommend = Integer.parseInt(request.getParameter("isRecommend"));
        ChildVenue childVenue = childVenueService.get(id);
        childVenue.setIsRecommend(isRecommend);
        int flag = childVenueService.update(childVenue);
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
        ModelAndView view = getModelAndView("sports/dialog/childVenueSelect");
        return view;
    }

}

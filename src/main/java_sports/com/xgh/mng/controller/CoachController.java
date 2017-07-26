package com.xgh.mng.controller;

import com.xgh.mng.basic.BaseController;
import com.xgh.mng.dao.IZoneDao;
import com.xgh.mng.entity.*;
import com.xgh.mng.sercices.ICoachCourseService;
import com.xgh.mng.sercices.ICoachService;
import com.xgh.mng.sercices.IFileDataServiceNew;
import com.xgh.mng.sercices.IKindsService;
import com.xgh.mng.services.IFileDataService;
import com.xgh.mng.services.IZoneService;
import com.xgh.util.ConstantUtil;
import com.xgh.util.JSONUtil;
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
 * Created by CQ on 2016/10/19.
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/sport/coach")
public class CoachController extends BaseController {

    @Autowired
    protected ICoachService coachService;

    @Autowired
    protected IKindsService kindsService;

    @Autowired
    protected ICoachCourseService coachCourseService;

    @Autowired
    protected IFileDataServiceNew fileDataService;

    @Autowired
    protected IZoneService zoneService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @InitBinder("coach")
    public void initBinder2(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("coach.");
    }


    @RequestMapping(value = "init")
    public ModelAndView init() {

        ModelAndView view = new ModelAndView();
        view.setViewName("sports/coach/init");
        view.addObject("toolbar", sysButtonService.getMenuButtons(request, getCurrentSysRoleId(), getCurrentUser()));
        view.addObject("gridComluns", sysGridColumnsService.getGridColumsByRequest(request));

        return view;
    }

    @RequestMapping(value = "/add")
    public ModelAndView add(@ModelAttribute Coach coach, @RequestParam String op) {
        ModelAndView view = new ModelAndView();
        view.addObject("op", op);
        if ("modify".equals(op)) {
            coach = coachService.get(coach.getId());
            long zoneId = coach.getData6();
            if(zoneId>0){
                String parFix = zoneService.get(zoneId).getPreFix();
                view.addObject("parFix",parFix);
            }
            long kindsId = coach.getSportId();
            Kinds kinds = kindsService.get(kindsId);
            if (kinds != null) {
                String preCode = kinds.getPreCode();
                view.addObject("preCode", preCode);
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("instId", getCurrentInstId());
                params.put("dataId", coach.getId());
                params.put("dataCode", ConstantUtil.FileUploadCode.Coach.value());
                params.put("dataVersion", 0);
                params.put("server", ConstantUtil.SERVER_URL);
                List<Map<String, Object>> fileDataList = fileDataService.getFileDatas(params);
                view.addObject("fileDataListJson", JSONUtil.getJson(fileDataList));
            }
        }
        view.addObject("coach", coach);
        view.setViewName("sports/coach/add");
        return view;
    }

    @RequestMapping(value = "/save")
    public void save(@ModelAttribute Coach coach, @RequestParam String op) {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (coach != null) {
            //判断添加修改,aom, 0 添加,1 修改
            int aom = coach.getId() > 1 ? 1 : 0;
            System.out.println("");
            String introduce = coach.getIntro();
            introduce = introduce.replace("<p>", "");
            introduce = introduce.replace("</p>", "");
            introduce = introduce.replace("<br />", "");
            coach.setIntro(introduce);
            int flg = coachService.save(request, coach, op);
            if (flg == 1) {
                if (aom == 0) {
                    System.out.println("");
                    resultMap = getResultMap("1", "添加成功!");
                } else
                    resultMap = getResultMap("1", "修改成功!");
            }else if(flg == 2){
                resultMap = getResultMap("0", "电话号码已经被注册，请更换!");
            }
            else {
                if (aom == 0) {
                    resultMap = getResultMap("0", "添加失败!");
                } else
                    resultMap = getResultMap("0", "修改失败!");
            }
        } else
            resultMap = getResultMap("0", "数据初始化失败!");
        outJson(resultMap);
    }

    /**
     *
     */
    @RequestMapping(value = "getList")
    public void getList() {
        outJson(coachService.getGridList(request));
    }



    /**
     *
     */
    @RequestMapping(value = "getCourseList")
    public void getCourseList() {

        outJson(coachCourseService.getGridList(request));
    }


    /**
     * 逻辑删除教练
     *
     * @param
     */
    @RequestMapping(value = "delete")
    public void delete(@ModelAttribute Coach coach) {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        Coach coach1 = coachService.get(coach.getId());
        coach1.setUpdateDate(new Date());
        coach1.setStatus(-1);
        int flg = coachService.update(coach1);
        if (flg == 1) {
            resultMap = getResultMap("1", "删除成功!");
        } else
            resultMap = getResultMap("0", "删除失败!");
        outJson(resultMap);
    }

    /**
     * 添加教练课程
     *
     * @param
     */
    @RequestMapping(value = "addCourse")
    public ModelAndView addCourse(@ModelAttribute CoachCourse coachCourse, @RequestParam String op) {

        ModelAndView view = new ModelAndView();
        view.addObject("op", op);
        view.setViewName("sports/coach/addCourse");
        String coachId = request.getParameter("coachId");//此处为教练id
        System.out.println(coachId);
        if ("modify".equals(op)) {
            coachCourse = coachCourseService.get(coachCourse.getId());
        }
        view.addObject("coachCourse", coachCourse);
        view.addObject("coachId", coachId);
        return view;
    }


    /**
     * 保存教练课程
     */

    @RequestMapping(value = "saveCourse")
    public void addEduCourse(@ModelAttribute CoachCourse coachCourse, @RequestParam String op) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (coachCourse != null) {
            int aom = coachCourse.getId() > 1 ? 1 : 0;
            int flg = coachCourseService.save(request, coachCourse, op);
            if (flg == 1) {
                if (aom == 0) {
                    System.out.println("11111");
                    resultMap = getResultMap("1", "添加课程成功!");
                } else
                    resultMap = getResultMap("1", "修改课程成功!");
            } else {
                if (aom == 0) {
                    System.out.println("22222222");
                    resultMap = getResultMap("0", "添加失败!");
                } else
                    resultMap = getResultMap("0", "修改失败!");
            }
        } else
            resultMap = getResultMap("0", "数据初始化失败!");
        outJson(resultMap);
    }

    /**
     * 教练课程表显示
     */

    @RequestMapping(value = "showCourseList")
    public ModelAndView showCourseList(@ModelAttribute Coach coach) {
        ModelAndView view = new ModelAndView();
        view.setViewName("sports/coach/showCourse");
        view.addObject("coachId", coach.getId());
        return view;
    }

    /**
     * 逻辑删除教练
     *
     * @param
     */
    @RequestMapping(value = "deleteCourse")
    public void deleteCourse(@ModelAttribute CoachCourse coachCourse) {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        CoachCourse coachCourse1 = coachCourseService.get(coachCourse.getId());
        coachCourse1.setUpdateDate(new Date());
        coachCourse1.setStatus(-1);
        int flg = coachCourseService.update(coachCourse1);
        if (flg == 1) {
            resultMap = getResultMap("1", "课程删除成功!");
        } else
            resultMap = getResultMap("0", "课程删除失败!");
        outJson(resultMap);
    }



    @RequestMapping(value = "recommendInit")
    public ModelAndView recommendInit(){
        ModelAndView view = new ModelAndView();
        view.setViewName("sports/recommend/coach");
        view.addObject("toolbar", sysButtonService.getMenuButtons(request, getCurrentSysRoleId(), getCurrentUser()));
        view.addObject("gridComluns", sysGridColumnsService.getGridColumsByRequest(request));

        return view;
    }

    /**
     * 热门场馆设置
     */
    @RequestMapping(value="update")
    public void update(){
        long id  = Long.parseLong(request.getParameter("id"));
        int isRecommend = Integer.parseInt(request.getParameter("isRecommend"));
        Coach coach = coachService.get(id);
        coach.setIsRecommend(isRecommend);
        int flag = coachService.update(coach);
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
        ModelAndView view = getModelAndView("sports/dialog/coachSelect");
        return view;
    }

    /**
     * 审核教练
     * @param coach
     * @return
     */
    @RequestMapping(value = "checkCoach")
    public void checkCoach(@ModelAttribute Coach coach){
        HashMap<String,Object> resultMap = new HashMap<String, Object>();
        coach.setUpdateDate(new Date());
        int flag = coachService.checkCoach(coach);
        if (flag > 0) {
            resultMap.put("success", true);
            resultMap.put("msg", "审核成功");
        } else {
            resultMap.put("success", false);
            resultMap.put("msg", "审核失败");
        }
        outJson(resultMap);
    }

    @RequestMapping(value = "/getListZone")
    public void getListZone(@RequestParam long parentId) {
        List list = this.zoneService.getTreeList(parentId, "0", 1);
        outJson(list);
    }

}

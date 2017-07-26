package com.xgh.mng.controller;

import com.xgh.mng.basic.BaseController;
import com.xgh.mng.entity.AosSubject;
import com.xgh.mng.entity.SysUnits;
import com.xgh.mng.sercices.IAosSubjectService;
import com.xgh.mng.sercices.IFileDataServiceNew;
import com.xgh.mng.sercices.ISubjectService;
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
 * Created by Administrator on 2016/11/10 0009.
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "business/aosSubject/")
public class AosSubjectController extends BaseController {

    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(KindsController.class);


    @Autowired
    protected IAosSubjectService aosSubjectService;

    @Autowired
    protected ISubjectService subjectService;


    @Autowired
    protected IFileDataServiceNew fileDataService;


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   //true:允许输入空值，false:不能为空值
    }

    @RequestMapping(value = "init")
    public ModelAndView init(){
        logger.info("init");
        //获取当前登录的公司
        ModelAndView view = getModelAndView("business/aosSubject/init");
        SysUnits sysUnits = getCurrentUnits();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("unitId", getCurrentUnitId());
        List<Map<String, Object>> treeList = subjectService.getSubjectTree(paramMap);
        view.addObject("treedata", JSONUtil.getJson(treeList));
        return view;

    }

    @RequestMapping(value = "add")
    public ModelAndView add(@ModelAttribute AosSubject aosSubject){
        ModelAndView view = new ModelAndView();
        if(aosSubject.getId()>0){
            aosSubject = aosSubjectService.get(aosSubject.getId());
        }
        view.addObject("aosSubject",aosSubject);
        view.setViewName("business/aosSubject/add");
        return view;
    }

    @RequestMapping(value = "/getList")
    public void getList(){
        logger.info("getList");
        outJson(aosSubjectService.getGridList(request));
    }

    @RequestMapping(value = "/save")
    public void save(@ModelAttribute AosSubject aosSubject){
        logger.info("save");
        Map<String,Object> resultMap = new HashMap<String, Object>();
        if (aosSubject != null) {
            int aom = aosSubject.getId() > 1 ? 1 : 0;
            int flg = aosSubjectService.save(request, aosSubject);
            if (flg == 1) {
                if (aom == 0) {
                    resultMap = getResultMap("1", "添加成功!");
                } else
                    resultMap = getResultMap("1", "修改成功!");
            } else {
                if (aom == 0) {
                    resultMap = getResultMap("1", "添加失败!");
                } else
                    resultMap = getResultMap("0", "修改失败!");
            }
        } else
            resultMap = getResultMap("0", "数据初始化失败!");
        outJson(resultMap);
    }

    @RequestMapping(value = "/delete")
    public void delete(@ModelAttribute AosSubject aosSubject) {
        logger.info("delete");
        Map<String, Object> resultMap = new HashMap<String, Object>();
        AosSubject aosSubject1 = aosSubjectService.get(aosSubject.getId());
        aosSubject1.setUpdateDate(new Date());
        aosSubject1.setStatus(-1);
        int flg = aosSubjectService.update(aosSubject1);
        if (flg == 1) {
            resultMap = getResultMap("1", "删除成功!");
        } else
            resultMap = getResultMap("0", "删除失败!");
        outJson(resultMap);
    }

    /**
     * 选择轮播图主题
     *
     * @return
     */
    @RequestMapping(value = "subjectInit")
    public ModelAndView subjectInit() {
        ModelAndView view = getModelAndView("business/aosSubject/subjectInit");
        if (null!=request.getParameter("isYes")&&!"".equals(request.getParameter("isYes"))){
            long isYes=Long.parseLong(request.getParameter("isYes"));
            view.addObject("isYes",isYes);
        }
        return view;
    }
}

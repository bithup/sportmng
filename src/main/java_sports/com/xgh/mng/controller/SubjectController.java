package com.xgh.mng.controller;

import com.xgh.mng.basic.BaseController;
import com.xgh.mng.entity.Kinds;
import com.xgh.mng.entity.Subject;
import com.xgh.mng.sercices.IFileDataServiceNew;
import com.xgh.mng.sercices.ISubjectService;
import com.xgh.util.ConstantUtil;
import com.xgh.util.JSONUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/7 0007.
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "business/subject/")
public class SubjectController extends BaseController {

    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(KindsController.class);

    @Autowired
    protected ISubjectService subjectService;

    @Autowired
    protected IFileDataServiceNew fileDataService;

    @RequestMapping(value = "init")
    public ModelAndView init(){
        return getModelAndView("business/subject/init");
    }

    @RequestMapping(value = "add")
    public ModelAndView add(@ModelAttribute Subject subject){
        ModelAndView view = new ModelAndView();
        view.setViewName("business/subject/add");
        if (subject != null && subject.getId() > 0) {
            //id>0 认为是修改操作
            subject = subjectService.get(subject.getId());
            String serverUrl = ConstantUtil.SERVER_URL;

            if (subject.getMobileFilePath() != null) {
                Map<String, Object> mobileImgData = new HashMap<String, Object>();
                mobileImgData.put("url", serverUrl + subject.getMobileFilePath());
                view.addObject("mobileImgData", JSONUtil.getJson(mobileImgData));
            }

            if (subject.getAndroidFilePath() != null) {
                Map<String, Object> androidImgData = new HashMap<String, Object>();
                androidImgData.put("url", serverUrl + subject.getAndroidFilePath());
                view.addObject("androidImgData", JSONUtil.getJson(androidImgData));
            }

            if (subject.getIosFilePath() != null) {
                Map<String, Object> iosImgData = new HashMap<String, Object>();
                iosImgData.put("url", serverUrl + subject.getMobileFilePath());
                view.addObject("iosImgData", JSONUtil.getJson(iosImgData));
            }

            if (subject.getPcFilePath() != null) {
                Map<String, Object> pcImgData = new HashMap<String, Object>();
                pcImgData.put("url", serverUrl + subject.getMobileFilePath());
                view.addObject("pcImgData", JSONUtil.getJson(pcImgData));
            }
            view.addObject("op", "modify");
        }
        //获取验证
        view.addObject("validationRules", sysValidationService.getValidationByCode("mngsubject_validate"));
        view.addObject("subject", subject);
        return view;
    }

    @RequestMapping(value = "/getList")
    public void getList(){
        logger.info("getList");
        outJson(subjectService.getGridList(request));
    }

    @RequestMapping(value = "/save")
    public void save(@ModelAttribute Subject subject){
        logger.info("save");
        Map<String,Object> resultMap = new HashMap<String, Object>();
        if (subject != null) {
            int aom = subject.getId() > 1 ? 1 : 0;
            int flg = subjectService.save(request, subject);
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

}

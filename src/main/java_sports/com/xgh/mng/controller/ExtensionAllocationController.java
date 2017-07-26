package com.xgh.mng.controller;

import com.xgh.mng.basic.BaseController;
import com.xgh.mng.entity.ExtensionAllocation;
import com.xgh.mng.sercices.IExtensionAllocationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: lcp
 * @Description:
 * @Date: 2017/6/19 0019
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "sport/extensionAllocation/")
public class ExtensionAllocationController extends BaseController {
    /**
     * @Fields serialVersionUID :
     */
    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(ExtensionAllocationController.class);


    @Autowired
    protected IExtensionAllocationService extensionAllocationService;


    @RequestMapping(value = "/init")
    public ModelAndView init() {
        logger.info("init");
        ModelAndView view = new ModelAndView();
        view.setViewName("sports/extensionAllocation/init");
        view.addObject("toolbar", sysButtonService.getMenuButtons(request, getCurrentSysRoleId(), getCurrentUser()));
        view.addObject("gridComluns", sysGridColumnsService.getGridColumsByRequest(request));


        return view;
    }

    @RequestMapping(value = "/add")
    public ModelAndView add(@ModelAttribute ExtensionAllocation extensionAllocation, @RequestParam String op) {
        ModelAndView view = new ModelAndView();
        view.addObject("op", op);
        if ("modify".equals(op)) {
            extensionAllocation = extensionAllocationService.get(extensionAllocation.getId());
        }
        view.addObject("extensionAllocation", extensionAllocation);
        view.setViewName("sports/extensionAllocation/add");
        return view;
    }

    @RequestMapping(value = "/getList")
    public void getList() {
        logger.info("getList");
        outJson(extensionAllocationService.getGridList(request));
    }

    @RequestMapping(value = "/save")
    public void save(@ModelAttribute ExtensionAllocation extensionAllocation) {
        logger.info("save");
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (extensionAllocation!=null) {
            int aom =extensionAllocation.getId() > 0 ? 1 : 0;
            int flag = extensionAllocationService.save(request, extensionAllocation);
            if (aom == 1) {
                if (flag == 1) {
                    resultMap = getResultMap("1", "修改成功!");
                } else{
                    resultMap = getResultMap("0", "修改失败!");
                }
            } else {
                if (flag == 1) {
                    resultMap = getResultMap("1", "添加成功!");
                } else if (flag == -1){
                    resultMap = getResultMap("-1", "推广类型已存在!");
                }else {
                    resultMap = getResultMap("0", "添加失败!");
                }
            }
        } else{
            resultMap = getResultMap("-2", "数据初始化失败!");
        }
        outJson(resultMap);
    }

    @RequestMapping(value = "/delete")
    public void delete(@ModelAttribute ExtensionAllocation extensionAllocation) {
        logger.info("delete");
        Map<String, Object> resultMap = new HashMap<String, Object>();
        ExtensionAllocation extensionAllocation1 = extensionAllocationService.get(extensionAllocation.getId());
        extensionAllocation1.setStatus(2);
        extensionAllocation1.setUpdateDate(new Date());
        int flag = extensionAllocationService.update(extensionAllocation1);
        if (flag == 1) {
            resultMap = getResultMap("1", "删除成功!");
        } else{
            resultMap = getResultMap("0", "删除失败!");
        }
        outJson(resultMap);
    }

}
package com.xgh.mng.controller;

import com.xgh.mng.basic.BaseController;
import com.xgh.mng.entity.WithdrawalsRate;
import com.xgh.mng.sercices.IWithdrawalsRateService;
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
 * @Author: cbj
 * @Description:
 * @Date: 2017/7/3
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "sport/withdrawalsRate/")
public class WithdrawalsRateController extends BaseController {
    /**
     * @Fields serialVersionUID :
     */
    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(WithdrawalsRateController.class);

    @Autowired
    protected IWithdrawalsRateService withdrawalsRateService;


    @RequestMapping(value = "/init")
    public ModelAndView init() {
        logger.info("init");
        ModelAndView view = new ModelAndView();
        view.setViewName("sports/withdrawalsRate/init");
        view.addObject("toolbar", sysButtonService.getMenuButtons(request, getCurrentSysRoleId(), getCurrentUser()));
        view.addObject("gridComluns", sysGridColumnsService.getGridColumsByRequest(request));
        return view;
    }

    @RequestMapping(value = "/add")
    public ModelAndView add(@ModelAttribute WithdrawalsRate withdrawalsRate, @RequestParam String op) {
        ModelAndView view = new ModelAndView();
        view.addObject("op", op);
        if ("modify".equals(op)) {
            withdrawalsRate = withdrawalsRateService.get(withdrawalsRate.getId());
        }
        view.addObject("withdrawalsRate", withdrawalsRate);
        view.setViewName("sports/withdrawalsRate/add");
        return view;
    }

    @RequestMapping(value = "/getList")
    public void getList() {
        logger.info("getList");
        outJson(withdrawalsRateService.getGridList(request));
    }

    @RequestMapping(value = "/save")
    public void save(@ModelAttribute WithdrawalsRate withdrawalsRate) {
        logger.info("save");
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (withdrawalsRate!=null) {
            int aom =withdrawalsRate.getId() > 0 ? 1 : 0;
            int flag = withdrawalsRateService.save(request, withdrawalsRate);
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
                    resultMap = getResultMap("-1", "已存在提现费规则");
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
    public void delete(@ModelAttribute WithdrawalsRate withdrawalsRate) {
        logger.info("delete");
        Map<String, Object> resultMap = new HashMap<String, Object>();
        WithdrawalsRate withdrawalsRate1= withdrawalsRateService.get(withdrawalsRate.getId());
        withdrawalsRate1.setStatus(2);
        withdrawalsRate1.setUpdateDate(new Date());
        int flag = withdrawalsRateService.update(withdrawalsRate1);
        if (flag == 1) {
            resultMap = getResultMap("1", "删除成功!");
        } else{
            resultMap = getResultMap("0", "删除失败!");
        }
        outJson(resultMap);
    }

}
package com.xgh.mng.controller;

import com.xgh.mng.basic.BaseController;
import com.xgh.mng.entity.ShortMessage;
import com.xgh.mng.sercices.IShortMessageService;
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

/**
 * Created by Administrator on 2016/11/23.
 */
@Controller
@Scope("prototype")
@RequestMapping("/sport/shortMessage/")
public class ShortMessageController extends BaseController{

    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(ShortMessage.class);

    @Autowired
    protected IShortMessageService shortMessageService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd ");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @InitBinder("shortMessage")
    public void initBinder2(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("shortMessage.");
    }

    @RequestMapping(value="init")
    public ModelAndView init(){
        ModelAndView view = new ModelAndView();
        view.setViewName("sports/shortMessage/init");
        view.addObject("toolbar", sysButtonService.getMenuButtons(request, getCurrentSysRoleId(), getCurrentUser()));
        view.addObject("gridComluns", this.sysGridColumnsService.getGridColumsByRequest(this.request));
        return view;
    }

    @RequestMapping(value="getList")
    public void getList(){
        outJson(shortMessageService.getGridPage(request));
    }
}

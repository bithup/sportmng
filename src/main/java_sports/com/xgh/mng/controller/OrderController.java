package com.xgh.mng.controller;

import com.xgh.mng.basic.BaseController;
import com.xgh.mng.entity.*;
import com.xgh.mng.sercices.*;
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
 * Created by CQ on 2016/11/7.
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/sport/order")
public class OrderController extends BaseController {

    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(KindsController.class);

    @Autowired
    protected IOrderService orderService;

    @Autowired
    protected IMemberUserService memberUserService;

    @Autowired
    protected IChildVenueService childVenueService;

    @Autowired
    protected ICoachService coachService;

    @Autowired
    protected IActivityService activityService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd ");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @InitBinder("order")
    public void initBinder2(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("order.");
    }

    @RequestMapping(value="init")
    public ModelAndView init(){
        ModelAndView view = new ModelAndView();
        view.setViewName("sports/order/init");
        view.addObject("toolbar", sysButtonService.getMenuButtons(request, getCurrentSysRoleId(), getCurrentUser()));
        view.addObject("gridComluns", this.sysGridColumnsService.getGridColumsByRequest(this.request));
        return view;
    }

    @RequestMapping(value = "getList")
    public void getList(){
        outJson(orderService.getGridList(request));
    }

    @RequestMapping(value="getDetail")
    public ModelAndView getDetail(){
        ModelAndView view = new ModelAndView();
        view.setViewName("sports/order/detail");
        long id = Integer.parseInt(request.getParameter("id"));
        Order order = orderService.get(id);
        MemberUser memberUser = memberUserService.get(order.getMemberId());
        if(order.getOrderType()==0){
            ChildVenue childVenue = childVenueService.get(order.getGoodsId());
            view.addObject("childVenue",childVenue);
        }else if(order.getOrderType()==1){
            Coach coach = coachService.get(order.getGoodsId());
            view.addObject("coach",coach);
        }else if(order.getOrderType()==2){
            Activity activity = activityService.get(order.getGoodsId());
            view.addObject("activity",activity);
        }
        view.addObject("order",order);
        view.addObject("memberUser",memberUser);
        return view;
    }

}

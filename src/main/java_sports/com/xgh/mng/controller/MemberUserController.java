package com.xgh.mng.controller;

import com.xgh.mng.basic.BaseController;
import com.xgh.mng.entity.MemberUser;
import com.xgh.mng.entity.Venue;
import com.xgh.mng.entity.Zone;
import com.xgh.mng.sercices.IFileDataServiceNew;
import com.xgh.mng.sercices.IMemberUserService;
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/7.
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/sport/MemberUser/")
public class MemberUserController extends BaseController{
    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(KindsController.class);

    @Autowired
    protected IMemberUserService memberUserService;

    @Autowired
    protected IZoneService zoneService;

    @Autowired
    protected IFileDataServiceNew fileDataService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd ");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   //true:允许输入空值，false:不能为空值
    }

    @InitBinder("memberUser")
    public void initBinder2(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("memberUser.");
    }


    @RequestMapping(value="init")
    public ModelAndView init(){
        ModelAndView view = new ModelAndView();
        view.setViewName("sports/memberUser/init");
        view.addObject("toolbar", sysButtonService.getMenuButtons(request, getCurrentSysRoleId(), getCurrentUser()));
        view.addObject("gridComluns", sysGridColumnsService.getGridColumsByRequest(request));
        return view;
    }

    @RequestMapping(value="getList")
    public void getList(){
        outJson(memberUserService.getGridList(request));
    }

    @RequestMapping(value = "add")
    public ModelAndView add(@ModelAttribute MemberUser memberUser, @RequestParam String op){
        ModelAndView view  = new ModelAndView();
        view.setViewName("sports/memberUser/add");
        view.addObject("op",op);
        if("modify".equals(op)){

            memberUser = memberUserService.get(memberUser.getId());
            if(memberUser.getZoneId()>0){
                String parFix = zoneService.get(memberUser.getZoneId()).getPreFix();
                view.addObject("parFix",parFix);
            }

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("instId", getCurrentInstId());
            params.put("dataId", memberUser.getId());
            params.put("dataCode", ConstantUtil.FileUploadCode.Member.value());
            params.put("dataVersion", 0);
            params.put("server", ConstantUtil.SERVER_URL);
            List<Map<String, Object>> fileDataList = fileDataService.getFileDatas(params);
            view.addObject("fileDataListJson", JSONUtil.getJson(fileDataList));
        }

        view.addObject(memberUser);
        return view;
    }

    @RequestMapping(value="save")
    public void save(@ModelAttribute MemberUser memberUser){
        Map<String,Object> resultMap = new HashMap<String, Object>();
        String status = request.getParameter("status");
        int flag = 0;
        if(null!=status&&!"".equals(status)){
            long id = Integer.parseInt(request.getParameter("id"));
            MemberUser member = memberUserService.get(id);
            member.setStatus(Integer.parseInt(status));
            flag = memberUserService.update(member);
            if(flag>0){
                resultMap.put("code",1);
                resultMap.put("msg","删除成功！");
            } else{
                resultMap.put("code",0);
                resultMap.put("msg","删除失败！");
            }
        }else{
            long id = memberUser.getId();
            flag = memberUserService.save(request,memberUser);
            if(id>0){
                if(flag>0){
                    resultMap.put("code",1);
                    resultMap.put("msg","修改成功！");
                }else {
                    resultMap.put("code",0);
                    resultMap.put("msg","修改失败！");
                }
            }else{
                if(flag==1){
                    resultMap.put("code",1);
                    resultMap.put("msg","添加成功！");
                }else if(flag==2){
                    resultMap.put("code",0);
                    resultMap.put("msg","此电话号已被注册！");
                } else {
                    resultMap.put("code",0);
                    resultMap.put("msg","添加失败！");
                }
            }
        }
        outJson(resultMap);
    }

    /**
     * 添加会员时去重
     */
    @RequestMapping(value = "/checkMember")
    public void checkMember(){
        HashMap<String,Object> resultMap = new HashMap<String, Object>();
        HashMap<String,Object> map = new HashMap<String, Object>();
        String account = request.getParameter("account");
        map.put("account",account);
        int num = memberUserService.getRepetitionMembers(map);
        if(num>0){
            resultMap.put("success",false);
            resultMap.put("msg","此号码已被注册！");
        }else{
            resultMap.put("success",true);
            resultMap.put("msg","此号码可用！");
        }
        outJson(resultMap);

    }

    @RequestMapping(value = "/getListZone")
    public void getListZone(@RequestParam long parentId) {
        List list = this.zoneService.getTreeList(parentId, "0", 1);
        outJson(list);
    }
    @RequestMapping(value = "/getListZone2")
    public void getListZone2(@RequestParam long id) {
        Zone zone = this.zoneService.get(id);
        outJson(zone);
    }
}

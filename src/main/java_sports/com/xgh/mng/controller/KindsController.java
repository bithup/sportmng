package com.xgh.mng.controller;

import com.xgh.mng.basic.BaseController;

import com.xgh.mng.entity.Kinds;
import com.xgh.mng.sercices.IFileDataServiceNew;
import com.xgh.mng.sercices.IKindsService;

import com.xgh.util.ConstantUtil;
import com.xgh.util.JSONUtil;

import com.xgh.util.PinyinUtil;
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


@Controller
@Scope("prototype")
@RequestMapping(value = "/business/kinds")
public class KindsController extends BaseController {
    /**
     * @Fields serialVersionUID :
     */
    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(KindsController.class);


    @Autowired
    protected IKindsService kindsService;

    @Autowired
    protected IFileDataServiceNew fileDataService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @InitBinder({"kinds"})
    public void initBinder1(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("kinds.");
    }

    /**
     * 初始化界面进入这个方法
     *
     * @return
     */
    @RequestMapping(value = "init")
    public ModelAndView init() {
        logger.info("init");
        ModelAndView view = new ModelAndView();
        view.setViewName("business/kinds/init");
        List list = this.kindsService.getChildTreeData(request, 0L);
        Map<String, Object> rootMap = new HashMap<String, Object>();
        rootMap.put("id", "0");
        rootMap.put("pid", "-1");
        rootMap.put("text", "所属分类");
        list.add(rootMap);
        view.addObject("treedata", JSONUtil.getJson(list));
        view.addObject("toolbar", sysButtonService.getMenuButtons(request, getCurrentSysRoleId(), getCurrentUser()));
        view.addObject("gridComluns", this.sysGridColumnsService.getGridColumsByRequest(this.request));
        return view;
    }

    /**
     * 展示列表数据
     *
     * @param op
     * @param pid
     */
    @RequestMapping(value = "/getList")
    public void getList(@RequestParam String op, @RequestParam long pid) {
        logger.info("getList");
        if (op.equals("grid")) {
            this.outJson(this.kindsService.getGridData(request, pid));
        }
    }

    /**
     * 点击添加触发的事件
     *
     * @param kinds
     * @param op
     * @return
     */
    @RequestMapping(value = "/add")
    public ModelAndView add(@ModelAttribute Kinds kinds, @RequestParam String op) {
        ModelAndView view = new ModelAndView();
        view.addObject("op", op);
        view.setViewName("business/kinds/add");
        logger.info("add");
        if ("modify".equals(op)) {
            kinds = kindsService.get(kinds.getId());
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("instId", getCurrentInstId());
            params.put("dataId", kinds.getId());
            params.put("dataCode", ConstantUtil.FileUploadCode.Kinds.value());
            params.put("dataVersion", 0);
            params.put("server", ConstantUtil.SERVER_URL);
            view.addObject("fileDataListJson", JSONUtil.getJson(fileDataService.getFileDatas(params)));
        }
        view.addObject("kinds", kinds);
        return view;
    }


    /**
     * 点击保存进入这个方法
     *
     * @param kinds
     * @param op
     */
    @RequestMapping(value = "/save")
    public void save(@ModelAttribute Kinds kinds, @RequestParam String op) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (kinds.getLevel() == 0) {
            kinds.setLevel(1);
        }
        if (kinds.getParentId() != 0) {
            Kinds kind2s = kindsService.get(kinds.getParentId());
            if (kind2s.getLevel() == 1) {
                kinds.setLevel(2);
            } else if (kind2s.getLevel() == 2) {
                kinds.setLevel(3);
            } else if (kind2s.getLevel() == 3) {
                kinds.setLevel(4);
            } else if (kind2s.getLevel() == 4) {
                kinds.setLevel(5);
            }
        }
        try {
            map.put("code", "1");
            map.put("msg", "保存成功！");
            kinds.setSpellName(PinyinUtil.getPinYin(kinds.getName()));
            kinds.setSpellFirstName(PinyinUtil.getPinYinHeadChar(kinds.getName()));
            this.kindsService.save(request, kinds, op);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            map.put("code", "0");
            map.put("msg", "保存失败！");
        }
        this.outJson(map);
    }

    /**
     * 获取当前级别，根据当前级别判断是否能添加数据
     *
     * @param kinds
     */
    @RequestMapping(value = "/getLevel")
    public void level(@ModelAttribute Kinds kinds) {
        Kinds kinds2 = kindsService.get(kinds.getId());
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (kinds2 != null && kinds2.getLevel() < 2) {
            resultMap.put("code", 1);
            resultMap.put("msg", "ok");
        } else if (kinds2 == null || "".equals(kinds2)) {
            resultMap.put("code", 1);
            resultMap.put("msg", "ok");
        } else {
            resultMap.put("code", 0);
            resultMap.put("msg", "fail");
        }
        outJson(resultMap);
    }

    /**
     * 逻辑删除数据
     *
     * @param kinds
     */
    @RequestMapping({"/delete"})
    public void delete(@ModelAttribute Kinds kinds) {
        HashMap map = new HashMap();
        long id = kinds.getId();
        Kinds kind = kindsService.get(id);
        logger.info("删除id......" + id);
        try {//判读是否有子集
            boolean isTrue = kindsService.isHasChild(kinds);
            if (isTrue) {
                map.put("code", "0");
                map.put("msg", "当前类型有子级，不可删除！");
            } else {
                kind.setStatus(-1);
                kindsService.update(kind);
                map.put("code", "1");
                map.put("msg", "删除成功！");
            }

        } catch (Exception var4) {
            logger.error(var4.getMessage(), var4);
            map.put("code", "0");
            map.put("msg", "删除失败！");
        }
        //返回的json数据。
        this.outJson(map);
    }

    /**
     * 通过parentId 获取子集
     *
     * @param kindsId
     */
    @RequestMapping(value = "getChildTreeData")
    public void getChildTreeData(@RequestParam long kindsId) {

        outJson(kindsService.getChildTreeData(request, kindsId));
    }

    @RequestMapping(value = "/getChildData")
    public void getChildData(@RequestParam long parentId) {
        List list = this.kindsService.getChildData(request, parentId);
        outJson(list);
    }

}
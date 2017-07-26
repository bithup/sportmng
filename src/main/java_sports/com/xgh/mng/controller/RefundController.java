package com.xgh.mng.controller;

import com.xgh.alipay.config.AlipayConfig;
import com.xgh.alipay.util.AlipaySubmit;
import com.xgh.alipay.util.UtilDate;
import com.xgh.mng.basic.BaseController;
import com.xgh.mng.entity.Order;
import com.xgh.mng.entity.Refund;
import com.xgh.mng.entity.SysUnits;
import com.xgh.mng.sercices.IOrderService;
import com.xgh.mng.sercices.IRefundService;
import com.xgh.util.JSONUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2016/11/8.
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/sport/refund")
public class RefundController extends BaseController{

    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(KindsController.class);

    @Autowired
    protected IRefundService refundService;

    @Autowired
    protected IOrderService orderService;

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
        view.setViewName("sports/refund/init");

        //获取当前登录的公司
        SysUnits sysUnits = getCurrentUnits();

        List<Map<String, Object>> treeList = new ArrayList<Map<String, Object>>();

        Map<String, Object> map0 = new HashMap<String, Object>();
        map0.put("id", 0);
        map0.put("text", sysUnits.getUnitName());
        treeList.add(map0);

        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("id", 1);
        map1.put("pid", 0);
        map1.put("text", "待处理退款申请");
        treeList.add(map1);

        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("id", 2);
        map2.put("pid", 0);
        map2.put("text", "全部退款申请");
        treeList.add(map2);
        view.addObject("treedata", JSONUtil.getJson(treeList));
        return view;
    }

    @RequestMapping(value="getList")
    public void getList(){
        outJson(refundService.getGridList(request));
    }

    /**
     * 支付宝退款
     * @return
     */
    @Transactional
    @RequestMapping(value="alipayRefundMoney")
    public ModelAndView alipayRefundMoney(){
        ModelAndView view = new ModelAndView();
        view.setViewName("sports/refund/alipay");
        long id = Long.parseLong(request.getParameter("id"));
        Refund refund = refundService.get(id);
        Order order = orderService.get(refund.getOrderId());

        String batch_no = UtilDate.getOrderNum()+UtilDate.getThree();
        refund.setBatchNo(batch_no);
        refund.setUpdateTime(new Date());
        refundService.update(refund);

        //把请求参数打包成map集合
        Map<String, String> sParaTemp = new HashMap<String, String>();
        sParaTemp.put("service", AlipayConfig.service);
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
        // sParaTemp.put("email","pyqxxs1564@sandbox.com");
        sParaTemp.put("notify_url", "http://yundongcmbs.fangshangqu.com/sportcmbs/alipay/refundNotifyCallBack.htm");
        sParaTemp.put("seller_user_id",AlipayConfig.seller_user_id);
        sParaTemp.put("batch_no",batch_no);//退款批次号：回调的时候根据此字段修改退款申请的状态
        sParaTemp.put("refund_date", AlipayConfig.refund_date);//退款请求时间
        sParaTemp.put("batch_num", "1");//退款总笔数
        //String detail_data ="2016092721001004060242786666^0.01^放上去商城退款";
        //tradNo = "2016092721001004060242786666";
        String detail_data = order.getTradeNo() + "^" + refund.getRefunndMoney() + "^" + refund.getReason();
        //String detail_data =tradNo+"^"+refundMoney+"^"+reason;//退款详情，格式为：“支付宝交易号^退款金额^退款说明”
        sParaTemp.put("detail_data",detail_data);
        //建立请求
        String sHtmlText = AlipaySubmit.buildRequest(sParaTemp,"get","确认");
        System.out.println(sHtmlText);
        view.addObject("sHtmlText",sHtmlText);

        return view;
    }


}

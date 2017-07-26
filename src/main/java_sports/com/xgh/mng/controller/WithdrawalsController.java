package com.xgh.mng.controller;

import com.xgh.mng.basic.BaseController;
import com.xgh.mng.entity.MemberUser;
import com.xgh.mng.entity.Withdrawals;
import com.xgh.mng.sercices.IMemberUserService;
import com.xgh.mng.sercices.IWithdrawalsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/23.
 */
@Controller
@Scope("prototype")
@RequestMapping(value="sport/withdrawals/")
public class WithdrawalsController extends BaseController{

    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(WithdrawalsController.class);

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd ");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @InitBinder("withdrawals")
    public void initBinder2(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("withdrawals.");
    }

    @Autowired
    protected IWithdrawalsService withdrawalsService;


    @Autowired
    protected IMemberUserService memberUserService;


    @RequestMapping(value="initAll")
    public ModelAndView initAll(){
        ModelAndView view = new ModelAndView();
        view.setViewName("sports/withdrawals/initAll");
        view.addObject("toolbar", sysButtonService.getMenuButtons(request, getCurrentSysRoleId(), getCurrentUser()));
        view.addObject("gridComluns", this.sysGridColumnsService.getGridColumsByRequest(this.request));
        return view;
    }


    @RequestMapping(value="init")
    public ModelAndView init(){
        ModelAndView view = new ModelAndView();
        view.setViewName("sports/withdrawals/init");
        view.addObject("toolbar", sysButtonService.getMenuButtons(request, getCurrentSysRoleId(), getCurrentUser()));
        view.addObject("gridComluns", this.sysGridColumnsService.getGridColumsByRequest(this.request));
        return view;
    }


    @RequestMapping(value="getList")
    public void getList(){
        outJson(withdrawalsService.getGridList(request));
    }


    @RequestMapping(value="buildTxt")
    public void buildTxt(){
        String file_path = "/webroot/UI/save_path/";
        /*String file_path = "d:/提现记录/";*/
        logger.info("file_path="+file_path);
        //String file_path ="D:/dp/bootstrap/UI/save_path/提现记录";
        File folder = new File(file_path);
        if (!folder.exists()) {
            folder.mkdir();
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date = sdf.format(new Date());
            String fName = date + "withdrawalsPage" + Integer.parseInt(request.getParameter("page"));
            String fileName = null;
            fileName = new String(fName.getBytes(),"utf-8");
            File txtFile = new File(file_path, fileName + ".txt");
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(txtFile),"UTF-8"));
            Map<String, Object> map = withdrawalsService.getGridList(request);
            List<Map<String, Object>> list = (List<Map<String, Object>>) map.get("Rows");
            for (Map<String, Object> drawMap : list) {
                String drawAccount = String.valueOf(drawMap.get("drawAccount"));
                String name = String.valueOf(drawMap.get("name"));
                double amount = 0;
                String realAmount = String.valueOf(drawMap.get("realAmount"));
                if (null != realAmount && !"".equals(realAmount) && !"null".equals(realAmount)) {
                    amount = Double.parseDouble(realAmount);
                }
                String createDate = String.valueOf(drawMap.get("createDate"));
                String createTime = createDate.substring(0,createDate.length()-2);
                //Date createTime = java.sql.Date.valueOf(String.valueOf(drawMap.get("createDate")));
                String xinxi = "账号：" + drawAccount + "     姓名：" + name + "     金额：" + amount + "     申请时间：" + createTime;
                //bw.write(new String(xinxi.getBytes(),"utf-8"));
                bw.write(xinxi);
                System.out.println(drawMap.get("createDate"));
                bw.write("\t\n");
                bw.newLine();
            }
            bw.newLine();
            bw.flush();
            bw.close();
            logger.info("path="+file_path + fileName + ".txt");
            HashMap<String,Object> resultMap = new HashMap<String, Object>();
            resultMap.put("code",1);
            // resultMap.put("filePath","http://122.114.4.130:9080/save_path/"+fileName + ".txt");
            resultMap.put("filePath",file_path + fileName + ".txt");
            outJson(resultMap);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping(value = "downLoad")
    public String downLoad() throws Exception {
        String filePath = request.getParameter("filePath");
        // File f = new File(filePath);
        File f = new File(new String(filePath.getBytes("ISO8859-1"),"utf-8"));
        BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
        byte[] buf = new byte[1024];
        int len = 0;
        response.reset();
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename="
                + toUTF8(f.getName()));
        response.flushBuffer();
        // OutputStream out = response.getOutputStream();
        BufferedOutputStream output = new BufferedOutputStream(response.getOutputStream());
        while ((len = br.read(buf)) > 0)
            output.write(buf, 0, len);
        output.flush();
        br.close();
        output.close();
        logger.info("下载结束");
        return null;
    }

    // UTF-8编码
    public String toUTF8(String s) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 0 && c <= 255) {
                sb.append(c);
            } else {
                byte[] b;
                try {
                    b = Character.toString(c).getBytes("utf-8");
                } catch (Exception ex) {
                    System.out.println(ex);
                    b = new byte[0];
                }
                for (int j = 0; j < b.length; j++) {
                    int k = b[j];
                    if (k < 0)
                        k += 256;
                    sb.append("%" + Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return sb.toString();
    }


    @RequestMapping("/get")
    public ModelAndView get() {
        logger.info("get");
        ModelAndView view = new ModelAndView();
        view.setViewName("sports/withdrawals/check");
        long id = Long.parseLong(request.getParameter("id"));
        Withdrawals withdrawals = withdrawalsService.get(id);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String newDate = sdf.format(withdrawals.getCreateDate());
        try {
            Date createDate = sdf.parse(newDate);
            withdrawals.setCreateDate(createDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        MemberUser memberUser = memberUserService.get(withdrawals.getMemberId());
        view.addObject("withdrawals", withdrawals);
        view.addObject("memberUser", memberUser);
        return view;
    }

    @RequestMapping("/save")
    public void save(@ModelAttribute Withdrawals withdrawals) {
        logger.info("save");
        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        int flag = withdrawalsService.update(request, withdrawals);
        if (flag > 0) {
            resultMap.put("code", 1);
            resultMap.put("msg", "操作成功！");
        } else {
            resultMap.put("code", 0);
            resultMap.put("msg", "操作失败！");
        }
        outJson(resultMap);
    }

}


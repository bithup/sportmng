package com.xgh.util;

import com.xgh.mng.dao.IShortMessageDao;
import com.xgh.mng.entity.ShortMessage;
import com.xgh.mng.sercices.IShortMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/28.
 */
public class ShortMessageUtil {

    @Autowired
    protected IShortMessageService shortMessageService;


    public ShortMessage sendMessage(String telPhone) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String content = "您已成功注册，账号为："+telPhone+",默认密码：123456。为确保您的账号安全，请尽快修改密码！请对本条短信保密，千万不要让他人看到哦！";
        String sendUrl = ConstantUtil.SEND_URL;
        System.out.println(sendUrl);
        String sendUrl1 = sendUrl.replace("{用户名}", URLEncoder.encode(ConstantUtil.SMS_ACCOUNT));
        String sendUrl2 = sendUrl1.replace("{短信密码}", URLEncoder.encode(ConstantUtil.SMS_PASSWORD));
        String sendUrl3 = sendUrl2.replace("{短信号码}", telPhone);
        String sendUrl4 = sendUrl3.replace("{短信内容}", content);

        String sTemp1 = sendUrl4.substring(0, sendUrl4.lastIndexOf("&") + 5);
        String sTemp2 = sendUrl4.substring(sendUrl4.lastIndexOf("&") + 5);

        try {
            sTemp2 = URLEncoder.encode(sTemp2, "gbk");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        sendUrl4 = sTemp1 + sTemp2;

        InputStream in = null;
        StringBuffer sb = new StringBuffer();
        try {
            URL url = new URL(sendUrl4);
            in = url.openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "GBK"));
            String inputLine = "";
            while ((inputLine = reader.readLine()) != null) {
                sb.append(inputLine);
            }
            System.out.println("短信批次号=" + sb.toString());
            /*return sb.toString();*/
            String[] result = sb.toString().split("&");
            String num = result[0].substring(result[0].lastIndexOf("=") + 1, result[0].length());
            String errId = result[4].substring(result[4].lastIndexOf("=") + 1, result[4].length());

            ShortMessage smsRecord = new ShortMessage();
            smsRecord.setPhoneNum(telPhone);
            smsRecord.setContent(content);
            smsRecord.setSendTime(new Date());
            if (!"0".equals(num)) {
                smsRecord.setSatatus(1);
            } else {
                smsRecord.setSatatus(Integer.parseInt(errId));
            }
          return smsRecord;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (Exception var13) {
                var13.printStackTrace();
            }
        }
        return null;
    }
}

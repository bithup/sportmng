package com.xgh.mng.sercices;

import com.xgh.mng.dao.IShortMessageDao;
import com.xgh.mng.entity.ShortMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/23.
 */
@Service("shortMessageService")
public class ShortMessageServiceImpl implements IShortMessageService {

    @Autowired
    protected IShortMessageDao shortMessageDao;

    public Map<String, Object> getGridPage(HttpServletRequest request) {
        Map<String,Object> gridMap = new HashMap<String, Object>();
        Map<String,Object> map = new HashMap<String, Object>();
        String page = request.getParameter("page");
        String pagesize = request.getParameter("pagesize");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        map.put("page",Integer.parseInt(page));
        map.put("pagesize",Integer.parseInt(pagesize));
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        List<ShortMessage> list = shortMessageDao.getListPage(map);
        for(ShortMessage message:list){
            if(message.getSatatus()!=null){
                if(message.getSatatus()==0){
                   message.setData1("失败");
                }else if(message.getSatatus()==1){
                    message.setData1("成功");
                }else if(message.getSatatus()==2){
                    message.setData1("未知");
                }
            }
        }
        gridMap.put("Rows",list);
        gridMap.put("Total",shortMessageDao.getRows(map));
        return gridMap;
    }

    public int insert(ShortMessage shortMessage) {
        return shortMessageDao.insert(shortMessage);
    }
}

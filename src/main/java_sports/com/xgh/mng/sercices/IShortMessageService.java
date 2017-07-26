package com.xgh.mng.sercices;

import com.xgh.mng.entity.ShortMessage;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/23.
 */
public interface IShortMessageService {

    public Map<String,Object> getGridPage(HttpServletRequest request);

    public int insert(ShortMessage shortMessage);
}

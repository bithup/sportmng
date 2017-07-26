package com.xgh.mng.sercices;

import com.xgh.mng.entity.Refund;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/8.
 */
public interface IRefundService {

    public int update(Refund refund);

    public Refund get(long id);

    public Map<String,Object> getGridList(HttpServletRequest request);
}

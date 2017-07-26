package com.xgh.mng.sercices;

import com.xgh.mng.entity.WithdrawalsRate;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Author: cbj
 * @Description:
 * @Date: 2017/7/3
 */
public interface IWithdrawalsRateService {

    public int update(WithdrawalsRate withdrawalsRate);

    public WithdrawalsRate get(long id);

    public List<Map<String, Object>> getList(Map<String, Object> map);

    public Map<String,Object> getGridList(HttpServletRequest request);

    public int save(HttpServletRequest request, WithdrawalsRate withdrawalsRate);
}

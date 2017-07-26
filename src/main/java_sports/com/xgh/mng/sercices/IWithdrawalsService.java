package com.xgh.mng.sercices;

import com.xgh.mng.entity.Withdrawals;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/23.
 */
public interface IWithdrawalsService {

    public Withdrawals get(long id);

    public Map<String,Object> getGridList(HttpServletRequest request);

    public int delete(long id);

    public int insert(Withdrawals withdrawals);

    public int update(HttpServletRequest request, Withdrawals withdrawals);
}

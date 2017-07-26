package com.xgh.mng.sercices;

import com.xgh.mng.basic.BaseService;
import com.xgh.mng.dao.IWithdrawalsRateDao;
import com.xgh.mng.entity.WithdrawalsRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
/**
 * @Author: cbj
 * @Description:
 * @Date: 2017/7/3
 */
@Service("withdrawalsRateService")
public class WithdrawalsRateServiceImpl extends BaseService implements IWithdrawalsRateService {

    @Autowired
    protected IWithdrawalsRateDao withdrawalsRateDao;

    public int update(WithdrawalsRate withdrawalsRate) {
        return withdrawalsRateDao.update(withdrawalsRate);
    }

    public WithdrawalsRate get(long id) {
        WithdrawalsRate withdrawalsRate= withdrawalsRateDao.get(id);
        return withdrawalsRate;
    }

    public List<Map<String, Object>> getList(Map<String, Object> map) {
        return withdrawalsRateDao.getList(map);
    }

    public Map<String, Object> getGridList(HttpServletRequest request) {
        Map<String, Object> gridMap = new HashMap<String, Object>();
        Map<String, Object> map = new HashMap<String, Object>();
        String page = request.getParameter("page");
        String pagesize = request.getParameter("pagesize");
        map.put("page", Integer.parseInt(page));
        map.put("pagesize", Integer.parseInt(pagesize));
        List<Map<String,Object>>  withdrawalsRateList= withdrawalsRateDao.getListMapPage(map);
        if (withdrawalsRateList == null) {
            withdrawalsRateList = new ArrayList<Map<String, Object>>();
        }
        for (Map<String,Object> withdrawalsRateList_:withdrawalsRateList){
            String withdrawalsDetail = "";
            int withdrawalsType = Integer.parseInt(String.valueOf(withdrawalsRateList_.get("withdrawalsType")));
            if (withdrawalsType==1){
                String withdrawalsPropor = String.valueOf(withdrawalsRateList_.get("withdrawalsPropor"));
                withdrawalsDetail = "提现费占比例"+withdrawalsPropor+"%";
            }else {
                String withdrawalsMoney = String.valueOf(withdrawalsRateList_.get("withdrawalsMoney"));
                withdrawalsDetail = "提现费每笔"+withdrawalsMoney+"元";
            }
            withdrawalsRateList_.put("withdrawalsDetail",withdrawalsDetail);
        }
        gridMap.put("Rows", withdrawalsRateList);
        gridMap.put("Total", withdrawalsRateDao.getRows(map));
        return gridMap;
    }

    public int save(HttpServletRequest request,WithdrawalsRate withdrawalsRate){
        int flag = 0;
        if (withdrawalsRate!=null&&withdrawalsRate.getId()>0){
            WithdrawalsRate withdrawalsRate1 = withdrawalsRateDao.get(withdrawalsRate.getId());
            withdrawalsRate1.setWithdrawalsType(withdrawalsRate.getWithdrawalsType());
            if (withdrawalsRate.getWithdrawalsType()==1){
                withdrawalsRate1.setWithdrawalsPropor(withdrawalsRate.getWithdrawalsPropor());
                withdrawalsRate1.setWithdrawalsMoney(null);
            }else {
                withdrawalsRate1.setWithdrawalsPropor(null);
                withdrawalsRate1.setWithdrawalsMoney(withdrawalsRate.getWithdrawalsMoney());
            }
            withdrawalsRate1.setEnableStatus(withdrawalsRate.getEnableStatus());
            withdrawalsRate1.setUpdateDate(new Date());
            flag = withdrawalsRateDao.update(withdrawalsRate1);
        }else {
            Map<String,Object> map = new HashMap<String, Object>();
            List<Map<String,Object>> withdrawalsRateList = withdrawalsRateDao.getListMapPage(map);
            if (withdrawalsRateList!=null&&withdrawalsRateList.size()>0){
                flag = -1;
            }else {
                withdrawalsRate.setCreateDate(new Date());
                withdrawalsRate.setStatus(1);
                flag = withdrawalsRateDao.add(withdrawalsRate);
            }
        }
        return flag;
    }

}
package com.xgh.mng.sercices;

import com.xgh.mng.basic.BaseService;
import com.xgh.mng.dao.IMemberUserDao;
import com.xgh.mng.dao.ITransactionRecordDao;
import com.xgh.mng.dao.IWithdrawalsDao;
import com.xgh.mng.entity.MemberUser;
import com.xgh.mng.entity.SysUnits;
import com.xgh.mng.entity.TransactionRecord;
import com.xgh.mng.entity.Withdrawals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/23.
 */
@Service("withdrawalsService")
public class WithdrawalsServiceImpl extends BaseService implements IWithdrawalsService {

    @Autowired
    protected IWithdrawalsDao withdrawalsDao;

    @Autowired
    protected ITransactionRecordDao transactionRecordDao;

    @Autowired
    protected IMemberUserDao memberUserDao;

    public Withdrawals get(long id) {
        return withdrawalsDao.get(id);
    }

    public Map<String, Object> getGridList(HttpServletRequest request) {

        Map<String,Object> gridMap = new HashMap<String, Object>();
        Map<String,Object> map = new HashMap<String, Object>();
        String drawStatus = request.getParameter("drawStatus");
        String page = request.getParameter("page");
        String pagesize = request.getParameter("pagesize");
        try {
            if(null!=request.getParameter("account")&&!"".equals(request.getParameter("account"))){
                String account = new String(request.getParameter("account").getBytes("ISO8859-1"),"utf-8");
                map.put("account",account);
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        map.put("drawStatus",drawStatus);
        map.put("page",Integer.parseInt(page));
        map.put("pagesize",Integer.parseInt(pagesize));
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        List<Map<String,Object>> list = withdrawalsDao.getListPage(map);
        for(Map<String,Object> drawMap:list){
            if(null!=drawMap.get("memberType").toString()&&!"".equals(drawMap.get("memberType").toString())){
                int type = Integer.parseInt(drawMap.get("memberType").toString());
                if(type==2){
                    drawMap.put("memberType","教练");
                }else if(type==3){
                    drawMap.put("memberType","场馆");
                }
            }

            if(null!=drawMap.get("drawStatus").toString()&&!"".equals(drawMap.get("drawStatus").toString())){
                int status = Integer.parseInt(drawMap.get("drawStatus").toString());
                if(status==0){
                    drawMap.put("drawStatus","待审核");
                }else if(status==1){
                    drawMap.put("drawStatus","已拒绝");
                }else if(status==2){
                    drawMap.put("drawStatus","已打款");
                }

            }
        }
        gridMap.put("Rows",list);
        gridMap.put("Total",withdrawalsDao.getRows(map));
        return gridMap;
    }

    public int delete(long id) {
        return withdrawalsDao.delete(id);
    }

    public int insert(Withdrawals withdrawals) {
        return withdrawalsDao.insert(withdrawals);
    }

    public int update(HttpServletRequest request,Withdrawals withdrawals) {
        int drawStatus = withdrawals.getDrawStatus();
        Withdrawals withdrawals1 = withdrawalsDao.get(withdrawals.getId());
        if(drawStatus==2){
            //往交易记录表插入一条记录
            TransactionRecord transactionRecord = new TransactionRecord();
            transactionRecord.setInstId(getCurrentInstId(request));
            SysUnits sysUnits = getCurrentUnits(request);
            transactionRecord.setInstCode(sysUnits.getInstCode());
            transactionRecord.setMemberId(withdrawals.getMemberId());
            transactionRecord.setMemberType(withdrawals.getMemberType());
            transactionRecord.setTransType(1);
            transactionRecord.setTransMoney(withdrawals.getCashWithdrawalAmount());
            transactionRecord.setTransTime(new Date());
            transactionRecord.setSources(4);
            transactionRecord.setCreateDate(new Date());
            transactionRecord.setUpdateDate(new Date());
            transactionRecord.setStatus(0);
            transactionRecordDao.insert(transactionRecord);
            withdrawals1.setPayTime(new Date());
        }else if(drawStatus==1){
            //拒绝提现将申请金额返回用户钱包
            MemberUser memberUser = memberUserDao.get(withdrawals.getMemberId());
            memberUser.setPurseBalance(formatDouble(memberUser.getPurseBalance()+withdrawals.getCashWithdrawalAmount()));
            memberUserDao.update(memberUser);
        }
        withdrawals1.setUpdateDate(new Date());
        withdrawals1.setDrawStatus(withdrawals.getDrawStatus());
        return withdrawalsDao.update(withdrawals1);
    }

    protected double formatDouble(double d){
        BigDecimal bigDecimal = BigDecimal.valueOf(d);
        return bigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}

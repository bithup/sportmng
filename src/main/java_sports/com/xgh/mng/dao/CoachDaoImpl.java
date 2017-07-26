package com.xgh.mng.dao;

import com.xgh.mng.dao.read.ICoachDaoR;
import com.xgh.mng.dao.write.ICoachDaoW;
import com.xgh.mng.entity.Coach;
import com.xgh.mng.entity.Kinds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by CQ on 2016/11/2.
 */
@Service("CoachDao")
public class CoachDaoImpl implements ICoachDao {

    @Autowired
    protected ICoachDaoR coachDaoR;
    @Autowired
    protected ICoachDaoW coachDaoW;


    public Coach get(long id) {
        return coachDaoR.get(id);
    }

    public List<Coach> getList(Coach coach) {
        return null;
    }

    public List<Map<String,Object>> getListPage(Map<String, Object> map) {
        return coachDaoR.getListPage(map);
    }

    public long getRows(Map<String, Object> map) {
        return coachDaoR.getRows(map);
    }

    public int add(Coach coach) {
        return coachDaoW.add(coach);
    }

    public int update(Coach coach) {
        return coachDaoW.update(coach);
    }

    public int deleteById(long id) {
        return 0;
    }

    public int deleteByNid(long nid) {
        return 0;
    }

    public int save(HttpServletRequest request, Coach coach, String op) {
        return coachDaoW.add(coach);
    }

    public int checkCoach(Coach coach){
        return coachDaoW.checkCoach(coach);
    }
}

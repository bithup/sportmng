package com.xgh.mng.dao;

import com.xgh.mng.dao.read.IMemberUserDaoR;
import com.xgh.mng.dao.write.IMemberUserDaoW;
import com.xgh.mng.entity.MemberUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/7.
 */
@Service("memberUserDao")
public class MemberUserDaoImpl implements IMemberUserDao{

    @Autowired
    protected IMemberUserDaoR memberUserDaoR;

    @Autowired
    protected IMemberUserDaoW memberUserDaoW;


    public int insert(MemberUser memberUser){
        return memberUserDaoW.insert(memberUser);
    }

    public int update(MemberUser memberUser){
        return memberUserDaoW.update(memberUser);
    }

    public int delete(long id){
        return memberUserDaoW.delete(id);
    }

    public MemberUser get(long id) {
        return memberUserDaoR.get(id);
    }

    public List<Map<String, Object>> getListPage(Map<String, Object> map) {
        return memberUserDaoR.getListPage(map);
    }

    public int getRows(Map<String, Object> map) {
        return memberUserDaoR.getRows(map);
    }

    public int getRepetitionMembers(Map<String, Object> map) {
        return memberUserDaoR.getRepetitionMembers(map);
    }
}

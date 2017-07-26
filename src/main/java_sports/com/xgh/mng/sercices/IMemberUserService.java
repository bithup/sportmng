package com.xgh.mng.sercices;

import com.xgh.mng.entity.MemberUser;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/7.
 */
public interface IMemberUserService {

    public int insert(MemberUser memberUser);

    public int update(MemberUser memberUser);

    public int delete(long id);

    public MemberUser get(long id);

    public Map<String,Object> getGridList(HttpServletRequest request);

    public int save(HttpServletRequest request,MemberUser memberUser);

    /**
     * 获取重复的会员
     *
     * @param map account 账号
     *            roleType 角色类型
     * @return
     */
    public int getRepetitionMembers(Map<String, Object> map);

}

package com.xgh.mng.dao.read;

import com.xgh.mng.entity.MemberUser;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/7.
 */
public interface IMemberUserDaoR {

    public MemberUser get(long id);

    public List<Map<String,Object>> getListPage(Map<String,Object> map);

    public int getRows(Map<String,Object> map);

    /**
     * 获取重复的会员
     *
     * @param map account 账号
     *            roleType 角色类型
     * @return
     */
    public int getRepetitionMembers(Map<String, Object> map);

}

package com.xgh.mng.sercices;

import com.xgh.mng.entity.AosSubject;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/10 0008.
 */
public interface IAosSubjectService {

    public int update(AosSubject aosSubject);

    public AosSubject get(long id);

    public List<AosSubject> getList(Map<String, Object> map);

    public Map<String,Object> getGridList(HttpServletRequest request);

    public int save(HttpServletRequest request, AosSubject aosSubject);
}

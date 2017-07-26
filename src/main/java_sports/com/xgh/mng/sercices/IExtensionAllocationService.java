package com.xgh.mng.sercices;

import com.xgh.mng.entity.ExtensionAllocation;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
/**
 * @Author: cbj
 * @Description:
 * @Date: 2017/7/4
 */
public interface IExtensionAllocationService {

    public int update(ExtensionAllocation extensionAllocation);

    public ExtensionAllocation get(long id);

    public List<Map<String, Object>> getList(Map<String, Object> map);

    public Map<String,Object> getGridList(HttpServletRequest request);

    public int save(HttpServletRequest request, ExtensionAllocation extensionAllocation);
}

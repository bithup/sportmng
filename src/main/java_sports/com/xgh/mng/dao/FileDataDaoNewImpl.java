package com.xgh.mng.dao;

import com.xgh.mng.dao.read.IFileDataDaoNewR;
import com.xgh.mng.dao.write.IFileDataDaoNeWW;
import com.xgh.mng.entity.FileData;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("fileDataNewDao")
public class FileDataDaoNewImpl
        implements IFileDataNewDao
{

    @Autowired
    protected IFileDataDaoNewR fileDataDaoR;

    @Autowired
    protected IFileDataDaoNeWW fileDataDaoW;

    public int add(FileData fileData)
    {
        return this.fileDataDaoW.add(fileData);
    }

    public int update(FileData fileData)
    {
        return this.fileDataDaoW.update(fileData);
    }

    public int deleteById(long id)
    {
        return this.fileDataDaoW.deleteById(id);
    }

    public int updateByDataSource(Map<String, Object> map)
    {
        return this.fileDataDaoW.updateByDataSource(map);
    }

    public int addBatch(List<FileData> list)
    {
        return this.fileDataDaoW.addBatch(list);
    }

    public FileData get(long id)
    {
        return this.fileDataDaoR.get(id);
    }

    public List<FileData> getList(Map<String, Object> map)
    {
        return this.fileDataDaoR.getList(map);
    }

    public List<FileData> getListPage(Map<String, Object> map)
    {
        return this.fileDataDaoR.getListPage(map);
    }

    public long getRows(Map<String, Object> map)
    {
        return this.fileDataDaoR.getRows(map);
    }
}
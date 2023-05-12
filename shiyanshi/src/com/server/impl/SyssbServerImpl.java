// 
// 
// 

package com.server.impl;

import java.util.List;
import java.util.Map;
import com.entity.Syssb;
import javax.annotation.Resource;
import com.dao.SyssbMapper;
import org.springframework.stereotype.Service;
import com.server.SyssbServer;

@Service
public class SyssbServerImpl implements SyssbServer
{
    @Resource
    private SyssbMapper dao;
    
    @Override
    public int add(final Syssb po) {
        return this.dao.add(po);
    }
    
    @Override
    public int update(final Syssb po) {
        return this.dao.update(po);
    }
    
    @Override
    public int delete(final int id) {
        return this.dao.delete(id);
    }
    
    @Override
    public List<Syssb> getAll(final Map<String, Object> map) {
        return this.dao.getAll(map);
    }
    
    @Override
    public List<Syssb> getByPage(final Map<String, Object> map) {
        return this.dao.getByPage(map);
    }
}

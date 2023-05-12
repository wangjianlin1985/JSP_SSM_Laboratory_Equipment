// 
// 
// 

package com.server.impl;

import java.util.List;
import java.util.Map;
import com.entity.ShiYan;
import javax.annotation.Resource;
import com.dao.ShiYanMapper;
import org.springframework.stereotype.Service;
import com.server.ShiYanServer;

@Service
public class ShiYanServerImpi implements ShiYanServer
{
    @Resource
    private ShiYanMapper gdao;
    
    @Override
    public int add(final ShiYan po) {
        return this.gdao.insert(po);
    }
    
    @Override
    public int update(final ShiYan po) {
        return this.gdao.updateByPrimaryKeySelective(po);
    }
    
    @Override
    public int delete(final int id) {
        return this.gdao.deleteByPrimaryKey(id);
    }
    
    @Override
    public List<ShiYan> getAll(final Map<String, Object> map) {
        return this.gdao.getAll(map);
    }
    
    @Override
    public ShiYan checkUname(final String account) {
        return null;
    }
    
    @Override
    public List<ShiYan> getByPage(final Map<String, Object> map) {
        return this.gdao.getByPage(map);
    }
    
    @Override
    public int getCount(final Map<String, Object> map) {
        return this.gdao.getCount(map);
    }
    
    @Override
    public List<ShiYan> select(final Map<String, Object> map) {
        return this.gdao.select(map);
    }
    
    @Override
    public ShiYan getById(final int id) {
        return this.gdao.selectByPrimaryKey(id);
    }
}

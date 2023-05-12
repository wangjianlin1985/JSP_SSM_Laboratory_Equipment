// 
// 
// 

package com.server.impl;

import com.entity.TongJi;
import java.util.List;
import java.util.Map;
import com.entity.Forder;
import javax.annotation.Resource;
import com.dao.ForderMapper;
import org.springframework.stereotype.Service;
import com.server.ForderServer;

@Service
public class ForderServerImpi implements ForderServer
{
    @Resource
    private ForderMapper gdao;
    
    @Override
    public int add(final Forder po) {
        return this.gdao.insert(po);
    }
    
    @Override
    public int update(final Forder po) {
        return this.gdao.updateByPrimaryKeySelective(po);
    }
    
    @Override
    public int delete(final int id) {
        return this.gdao.deleteByPrimaryKey(id);
    }
    
    @Override
    public List<Forder> getAll(final Map<String, Object> map) {
        return this.gdao.getAll(map);
    }
    
    @Override
    public Forder checkUname(final String account) {
        return null;
    }
    
    @Override
    public List<Forder> getByPage(final Map<String, Object> map) {
        return this.gdao.getByPage(map);
    }
    
    @Override
    public int getCount(final Map<String, Object> map) {
        return this.gdao.getCount(map);
    }
    
    @Override
    public List<Forder> select(final Map<String, Object> map) {
        return this.gdao.select(map);
    }
    
    @Override
    public Forder getById(final int id) {
        return this.gdao.selectByPrimaryKey(id);
    }
    
    @Override
    public List<Forder> showTop10(final Map<String, Object> map) {
        return this.gdao.showTop10(map);
    }
    
    @Override
    public List<TongJi> getTongJi(final Map<String, Object> map) {
        return this.gdao.getTongJi(map);
    }
}

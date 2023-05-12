// 
// 
// 

package com.server.impl;

import java.util.List;
import com.entity.Sysuser;
import java.util.Map;
import javax.annotation.Resource;
import com.dao.SysuserMapper;
import org.springframework.stereotype.Service;
import com.server.SysuserServier;

@Service
public class SysuserServiceImpi implements SysuserServier
{
    @Resource
    private SysuserMapper userdao;
    
    @Override
    public Sysuser adminLogin(final Map<String, Object> po) {
        return this.userdao.adminLogin(po);
    }
    
    @Override
    public int add(final Sysuser po) {
        return this.userdao.insert(po);
    }
    
    @Override
    public int update(final Sysuser po) {
        return this.userdao.updateByPrimaryKeySelective(po);
    }
    
    @Override
    public int delete(final int id) {
        return this.userdao.deleteByPrimaryKey(id);
    }
    
    @Override
    public List<Sysuser> getAll(final Map<String, Object> map) {
        return this.userdao.getAll(map);
    }
    
    @Override
    public Sysuser checkUname(final Map<String, Object> account) {
        return this.userdao.checkUname(account);
    }
    
    @Override
    public List<Sysuser> getByPage(final Map<String, Object> map) {
        return this.userdao.getByPage(map);
    }
    
    @Override
    public int getCount(final Map<String, Object> po) {
        return this.userdao.getCount(po);
    }
    
    @Override
    public List<Sysuser> select(final Map<String, Object> map) {
        return this.userdao.select(map);
    }
    
    @Override
    public Sysuser getById(final int id) {
        return this.userdao.selectByPrimaryKey(id);
    }
}

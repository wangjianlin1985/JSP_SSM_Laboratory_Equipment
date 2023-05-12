// 
// 
// 

package com.dao;

import java.util.List;
import java.util.Map;
import com.entity.Sysuser;

public interface SysuserMapper
{
    int deleteByPrimaryKey(Integer p0);
    
    int insert(Sysuser p0);
    
    int insertSelective(Sysuser p0);
    
    Sysuser selectByPrimaryKey(Integer p0);
    
    int updateByPrimaryKeySelective(Sysuser p0);
    
    int updateByPrimaryKey(Sysuser p0);
    
    Sysuser checkUname(Map<String, Object> p0);
    
    Sysuser adminLogin(Map<String, Object> p0);
    
    List<Sysuser> getAll(Map<String, Object> p0);
    
    int getCount(Map<String, Object> p0);
    
    List<Sysuser> getByPage(Map<String, Object> p0);
    
    List<Sysuser> select(Map<String, Object> p0);
}

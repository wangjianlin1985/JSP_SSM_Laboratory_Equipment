// 
// 
// 

package com.server;

import java.util.List;
import com.entity.Sysuser;
import java.util.Map;

public interface SysuserServier
{
    Sysuser adminLogin(Map<String, Object> p0);
    
    int add(Sysuser p0);
    
    Sysuser getById(int p0);
    
    int update(Sysuser p0);
    
    int delete(int p0);
    
    List<Sysuser> getAll(Map<String, Object> p0);
    
    Sysuser checkUname(Map<String, Object> p0);
    
    List<Sysuser> getByPage(Map<String, Object> p0);
    
    int getCount(Map<String, Object> p0);
    
    List<Sysuser> select(Map<String, Object> p0);
}

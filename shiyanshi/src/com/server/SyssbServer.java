// 
// 
// 

package com.server;

import java.util.List;
import java.util.Map;
import com.entity.Syssb;

public interface SyssbServer
{
    int add(Syssb p0);
    
    int update(Syssb p0);
    
    int delete(int p0);
    
    List<Syssb> getAll(Map<String, Object> p0);
    
    List<Syssb> getByPage(Map<String, Object> p0);
}

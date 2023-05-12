// 
// 
// 

package com.server;

import java.util.List;
import java.util.Map;
import com.entity.ShiYan;

public interface ShiYanServer
{
    int add(ShiYan p0);
    
    int update(ShiYan p0);
    
    int delete(int p0);
    
    List<ShiYan> getAll(Map<String, Object> p0);
    
    ShiYan checkUname(String p0);
    
    ShiYan getById(int p0);
    
    List<ShiYan> getByPage(Map<String, Object> p0);
    
    int getCount(Map<String, Object> p0);
    
    List<ShiYan> select(Map<String, Object> p0);
}

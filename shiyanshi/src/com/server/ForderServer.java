// 
// 
// 

package com.server;

import com.entity.TongJi;
import java.util.List;
import java.util.Map;
import com.entity.Forder;

public interface ForderServer
{
    int add(Forder p0);
    
    int update(Forder p0);
    
    int delete(int p0);
    
    List<Forder> getAll(Map<String, Object> p0);
    
    Forder checkUname(String p0);
    
    Forder getById(int p0);
    
    List<Forder> getByPage(Map<String, Object> p0);
    
    int getCount(Map<String, Object> p0);
    
    List<Forder> select(Map<String, Object> p0);
    
    List<Forder> showTop10(Map<String, Object> p0);
    
    List<TongJi> getTongJi(Map<String, Object> p0);
}

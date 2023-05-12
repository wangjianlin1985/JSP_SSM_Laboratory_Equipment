// 
// 
// 

package com.dao;

import com.entity.TongJi;
import java.util.List;
import java.util.Map;
import com.entity.Forder;

public interface ForderMapper
{
    int deleteByPrimaryKey(Integer p0);
    
    int insert(Forder p0);
    
    int insertSelective(Forder p0);
    
    Forder selectByPrimaryKey(Integer p0);
    
    int updateByPrimaryKeySelective(Forder p0);
    
    int updateByPrimaryKey(Forder p0);
    
    Forder checkUname(Map<String, Object> p0);
    
    List<Forder> getAll(Map<String, Object> p0);
    
    int getCount(Map<String, Object> p0);
    
    List<Forder> getByPage(Map<String, Object> p0);
    
    List<Forder> select(Map<String, Object> p0);
    
    List<Forder> showTop10(Map<String, Object> p0);
    
    List<TongJi> getTongJi(Map<String, Object> p0);
}

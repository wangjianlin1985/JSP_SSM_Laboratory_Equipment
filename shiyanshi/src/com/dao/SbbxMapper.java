// 
// 
// 

package com.dao;

import java.util.Map;
import java.util.List;
import com.entity.Sbbx;

public interface SbbxMapper
{
    int deleteByPrimaryKey(Integer p0);
    
    int insert(Sbbx p0);
    
    int insertSelective(Sbbx p0);
    
    Sbbx selectByPrimaryKey(Integer p0);
    
    int updateByPrimaryKeySelective(Sbbx p0);
    
    int updateByPrimaryKey(Sbbx p0);
    
    List<Sbbx> getAll();
    
    int getCount(Map<String, Object> p0);
    
    List<Sbbx> getByPage(Map<String, Object> p0);
    
    List<Sbbx> select(Map<String, Object> p0);
}

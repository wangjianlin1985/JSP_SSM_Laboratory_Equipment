// 
// 
// 

package com.dao;

import java.util.List;
import java.util.Map;
import com.entity.ShiYan;

public interface ShiYanMapper
{
    int deleteByPrimaryKey(Integer p0);
    
    int insert(ShiYan p0);
    
    int insertSelective(ShiYan p0);
    
    ShiYan selectByPrimaryKey(Integer p0);
    
    int updateByPrimaryKeySelective(ShiYan p0);
    
    int updateByPrimaryKeyWithBLOBs(ShiYan p0);
    
    int updateByPrimaryKey(ShiYan p0);
    
    ShiYan checkUname(Map<String, Object> p0);
    
    List<ShiYan> getAll(Map<String, Object> p0);
    
    int getCount(Map<String, Object> p0);
    
    List<ShiYan> getByPage(Map<String, Object> p0);
    
    List<ShiYan> select(Map<String, Object> p0);
}

// 
// 
// 

package com.dao;

import java.util.List;
import com.entity.Sbbf;

public interface SbbfMapper
{
    int deleteByPrimaryKey(Integer p0);
    
    int insert(Sbbf p0);
    
    int insertSelective(Sbbf p0);
    
    Sbbf selectByPrimaryKey(Integer p0);
    
    int updateByPrimaryKeySelective(Sbbf p0);
    
    int updateByPrimaryKey(Sbbf p0);
    
    List<Sbbf> getAll();
}

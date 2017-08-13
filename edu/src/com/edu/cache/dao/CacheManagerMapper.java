/**  
 * @Title: CacheManagerMapper.java
 * @Package com.netbox.service.common.dao
 * @Description: TODO
 * @author cjy
 * @date 2017-6-6 下午4:29:25
 */
package com.edu.cache.dao;

import java.util.List;
import java.util.Map;

import com.edu.cache.entity.CacheDataEntity;

/**
 * @Description: TODO
 * @author cjy
 * @date 2017-6-6 下午4:29:25
 */
public interface CacheManagerMapper {

    public List<Map<String, Object>> getDataFromDBSaveToTempCache(CacheDataEntity dto);
    
    
}

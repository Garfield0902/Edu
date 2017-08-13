package com.edu.cache.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.cache.CacheManager;
import com.edu.cache.constant.Constants;
import com.edu.cache.dao.CacheManagerMapper;
import com.edu.cache.entity.CacheDataEntity;
import com.edu.cache.service.PullDataToCache;
import com.edu.cache.util.XmlFileOpe;

@Service
public class PullDataToCacheImpl implements PullDataToCache {
    private static final Log log = LogFactory.getLog(PullDataToCacheImpl.class);

    @Autowired
    public CacheManager cacheManager;

    @Autowired
    private CacheManagerMapper mapper;

    private static List<CacheDataEntity> cacheTableInfoList = new ArrayList<CacheDataEntity>();

    /**
     * 拉取xml配置的数据库缓存
     */
    @Override
    public void pullData() {
        try {
            // 解析xml文件,并将要缓存的表信息保存入cacheTableInfoList;
            parseCacheXml();
            // 从数据库获取数据，并保存数据
            getDataFromDBSaveToTempCache();
            // 放入缓存中
            SaveToCacheManager();
            test();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    private void test() {
    	List<Map<String,Object>> list = cacheManager.getCacheData("edu_dbo_sys_datadictionary");
    	for(Map<String,Object> m :list){
    		System.out.println(m.keySet());
    	}
	}

	/**
     * 拉取逻辑缓存
     */
    @Override
    public void pullLogicData() {
        // final ConcurrentMap<String, CacheEntity> cache =
        // cacheManager.getCache();
        // 加载 初始化区域和设备的缓存
        /**
         * 后面可以添加其他的缓存
         */
    }

    private void SaveToCacheManager() {
        for (CacheDataEntity ce : cacheTableInfoList) {
            String key = ce.getKey();
            cacheManager.put(key, ce, true);
        }
        cacheTableInfoList.clear();
    }

    private void getDataFromDBSaveToTempCache() throws Exception {
        for (CacheDataEntity ce : cacheTableInfoList) {
            try {
                // 查询数据
                List<Map<String, Object>> list = mapper.getDataFromDBSaveToTempCache(ce);

                if (null == ce.getList()) {
                    ce.setList(new ArrayList<Map<String, Object>>());
                }
                // 判断是否需要把全量数据添加到缓存中
                if ("true".equals(ce.getFullCache())) {
                    // 添加全量数据到缓存中
                    ce.getList().addAll(list);
                }
                // 把需要独立成map的字段遍历，并添加到临时map中
                cacheDataToMap(ce, list);
            } catch(Exception e) {
                log.error("缓存管理-->添加缓存:查询数据库报错", e);
            }
        }
    }

    /**
     * 
     * @author cjy
     * @Description: 针对配置需要独立成一个map的缓存数据进行遍历，并保存到全局map中
     * @param @param ce
     * @return void
     * @date 2017-6-8
     */
    private void cacheDataToMap(CacheDataEntity ce, List<Map<String, Object>> list) {
        // 配置并遍历所有需要单独成map的字段
        String toMapFiled = ce.getToMapField();
        // 判断需要把数据独立成map的配置是否为空
        if (StringUtils.isEmpty(toMapFiled)) {
            log.info("缓存管理-->添加缓存:" + ce.getTable() + "表没有要独立成map的字段返回!");
            return;
        }

        if (null == list || list.size() == 0) {
            log.info("缓存管理-->添加缓存:" + ce.getTable() + "表没有查询出有效的数据返回!");
            return;
        }

        String[] fields = toMapFiled.split(",", -1);
        for (int i = 0; i < fields.length; i++) {
            String field = fields[i];
            // 获取哪个字段需要独立成map
            String[] keys = field.split(":", -1);
            if (null == keys || keys.length != 3) {
                log.info("缓存管理-->添加缓存:把数据添加到缓存map中出错，原因:" + ce.getTable() + "表配置的独立缓存没有配置!");
                continue;
            }
            String tmpKey = keys[0];
            String key = keys[1];
            String value = keys[2];
            log.debug("缓存管理-->添加缓存:独立缓存" + ce.getTable() + "表设置了独立map缓存，其中取独立缓存的key:" + tmpKey + " 缓存是以" + key + "字段值做key " + value + "字段值做value!");
            
            Map<String, Object> tmp = new HashMap<String, Object>();
            // 遍历所有的数据，并把数据添加到临时map中
            for (Map<String, Object> data : list) {
                tmp.put(data.get(key).toString(), data.get(value));
            }
            // 改组字段遍历完成，把临时map添加到全局对象中
            ce.getCacheMapData().put(tmpKey, tmp);
        }
    }

    /**
     * 解析缓存的xml文件
     */
    private void parseCacheXml() {
        String filePath = Constants.cacheXmlPath;
        Document doc = null;
        try {
            doc = XmlFileOpe.loadFileByPath(filePath);
            Element ele = XmlFileOpe.rootElement(doc);
            List<Element> fileNodes = ele.selectNodes("/cacheManager/cache");
            for (Element e : fileNodes) {
                CacheDataEntity ce = new CacheDataEntity();
                ce.setDbName(e.attributeValue("dbName"));
                ce.setDbo(e.attributeValue("dbo"));
                ce.setTable(e.attributeValue("table"));
                ce.setKey(e.attributeValue("dbName") + "_" + e.attributeValue("dbo") + "_" + e.attributeValue("table"));
                String columns = StringUtils.isEmpty(e.attributeValue("columns")) ? "*" : e.attributeValue("columns");
                ce.setColumns(columns);
                ce.setConditions(e.attributeValue("conditions"));
                ce.setToMapField(e.attributeValue("toMapField"));
                String fullCache = StringUtils.isEmpty(e.attributeValue("fullCache")) ? "true" : e
                        .attributeValue("fullCache");
                ce.setFullCache(fullCache);
                ce.setCacheMapData(new HashMap<String, Map<String, Object>>());
                cacheTableInfoList.add(ce);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }
}
